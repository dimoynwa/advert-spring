package com.sirma.advertisement.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.sirma.advertisement.api.entity.audit.UserDateAudit;

@Entity
@Table(name="info_file")
public class InformationFile extends UserDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;
    
    private Boolean avatar;

    @Lob
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;
    
	public InformationFile() {
		super();
	}
	
	public InformationFile(String id, String fileName, String fileType, Boolean avatar, byte[] data, JobPost jobPost) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.avatar = avatar;
		this.data = data;
		this.jobPost = jobPost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Boolean getAvatar() {
		return avatar;
	}

	public void setAvatar(Boolean avatar) {
		this.avatar = avatar;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public JobPost getJobPost() {
		return jobPost;
	}

	public void setJobPost(JobPost jobPost) {
		this.jobPost = jobPost;
	}

}
