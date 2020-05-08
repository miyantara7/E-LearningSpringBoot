package com.lawencon.elearning.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_file_question_headers")
public class FileQuestionHeader {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private String headerQuestionId;

	@ManyToOne
	@JoinColumn(name = "materipengajar_id")
	private MateriPengajar materiPengajar;
	private String title;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style = "yyyy-MM-dd")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style = "yyyy-MM-dd")
	private Date endDate;

	@DateTimeFormat(style = "HH:mm")
	private LocalTime startTime;
	@DateTimeFormat(style = "HH:mm")
	private LocalTime endTime;
	private String day;
	private String type;

	public String getHeaderQuestionId() {
		return headerQuestionId;
	}

	public void setHeaderQuestionId(String headerSoalId) {
		this.headerQuestionId = headerSoalId;
	}

	public MateriPengajar getMateriPengajar() {
		return materiPengajar;
	}

	public void setMateriPengajar(MateriPengajar materiPengajar) {
		this.materiPengajar = materiPengajar;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
