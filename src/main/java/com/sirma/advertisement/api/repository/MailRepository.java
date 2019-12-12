package com.sirma.advertisement.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sirma.advertisement.api.entity.Mail;

public interface MailRepository extends JpaRepository<Mail, Long> {

	@EntityGraph(value="mail_with_user", type=EntityGraphType.FETCH)
	List<Mail> findBySuccess(Boolean success, Pageable pageble);
}
