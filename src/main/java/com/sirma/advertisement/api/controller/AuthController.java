package com.sirma.advertisement.api.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sirma.advertisement.api.model.ForgotPasswordRequest;
import com.sirma.advertisement.api.model.JwtAuthenticationResponse;
import com.sirma.advertisement.api.model.LoginRequest;
import com.sirma.advertisement.api.model.ResetPasswordRequest;
import com.sirma.advertisement.api.model.SignUpRequest;
import com.sirma.advertisement.api.model.UserInfo;
import com.sirma.advertisement.api.security.JwtTokenProvider;
import com.sirma.advertisement.api.service.AuthService;
import com.sirma.advertisement.api.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;
    
    @Autowired
    AuthService authService;
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request ,@Valid @RequestBody LoginRequest loginRequest) {
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
    
    @PostMapping(value = "/signup", consumes = { "multipart/form-data" })
    public ResponseEntity<?> registerUser(@Valid @RequestPart("signUpRequest") SignUpRequest signUpRequest, @RequestPart("avatar") MultipartFile avatar) throws Exception {
    	UserInfo userInfo = userService.registerUser(signUpRequest, avatar);
    	URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(userInfo.getUserName()).toUri();
    	return ResponseEntity.created(location).body(userInfo);
    }
    
    @PostMapping("/forgotPassword")
    public ResponseEntity<?> processForgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest request) {
    	return ResponseEntity.ok(authService.forgotPasswordGenerateToken(forgotPasswordRequest, request));
    }
    
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
    	return ResponseEntity.ok(authService.resetPassword(resetPasswordRequest));
    }
    
    @PutMapping("/activate/{token}")
    public ResponseEntity<?> activateUser(@PathVariable("token") String token) {
    	UserInfo ui = authService.activateUser(token);
    	return ResponseEntity.ok(ui);
    }
    
    @GetMapping("/check/username")
    public ResponseEntity<?> checkUsername(@RequestParam(value = "username") String username) {
    	userService.checkUsername(username);
    	return ResponseEntity.ok(username);
    }
    
    @GetMapping("/check/email")
    public ResponseEntity<?> checkEmail(@RequestParam(value = "email") String email) {
    	userService.checkEmail(email);
    	return ResponseEntity.ok(email);
    }
}
