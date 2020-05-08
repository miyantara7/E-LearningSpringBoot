package com.lawencon.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_forum_quiz_headers")
public class ForumQuizHeader {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private String id;
	@ManyToOne
	@JoinColumn(name = "file_question_header_id")
	private FileQuestionHeader fileQuestionHeader;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FileQuestionHeader getFileQuestionHeader() {
		return fileQuestionHeader;
	}

	public void setFileQuestionHeader(FileQuestionHeader fileQuestionHeader) {
		this.fileQuestionHeader = fileQuestionHeader;
	}

}
