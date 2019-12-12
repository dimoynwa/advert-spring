package com.sirma.advertisement.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToOne;

import com.sirma.advertisement.api.entity.audit.UserDateAudit;

@NamedEntityGraphs(value= {@NamedEntityGraph(name="mail_with_user",
		attributeNodes={@NamedAttributeNode(value="receiver", subgraph="user")}, 
		subgraphs={@NamedSubgraph(name="user", attributeNodes={@NamedAttributeNode(value="roles"),}),})})
@Entity
public class Mail extends UserDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="template")
	private String template;
	
	@Column(name="text", columnDefinition="text")
	private String text;
	
	@Column(name="subject")
	private String subject;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "receiver_id")
    private User receiver;
	
	@Column(name="success")
	private Boolean success = false;
	
	@Column(name="error_message")
	private String errorMessage;

	@Column(name="retry_count")
	private Integer retryCount = 0;
	
	public Mail() {
		super();
	}

	public Mail(Long id, String template, String text, String subject, User receiver, Boolean success, String errorMessage, Integer retryCount) {
		super();
		this.id = id;
		this.template = template;
		this.text = text;
		this.subject = subject;
		this.receiver = receiver;
		this.success = success;
		this.errorMessage = errorMessage;
		this.retryCount = retryCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	@Override
	public String toString() {
		return "Mail [id=" + id + ", template=" + template + ", text=" + text + ", subject=" + subject + ", receiver="
				+ receiver + ", success=" + success + ", errorMessage=" + errorMessage + ", retryCount=" + retryCount
				+ "]";
	}

}
