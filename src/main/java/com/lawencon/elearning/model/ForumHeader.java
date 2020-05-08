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
@Table(name = "tb_forum_headers")
public class ForumHeader {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private String id;
	@ManyToOne
	@JoinColumn(name = "topic_id")
	private MateriHeader materiHeader;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MateriHeader getMateriHeader() {
		return materiHeader;
	}

	public void setMateriHeader(MateriHeader materiHeader) {
		this.materiHeader = materiHeader;
	}
}
