package com.lawencon.elearning.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_final_scores")
public class FinalScore {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private String id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
	private double finalScore;
	@ManyToOne
	@JoinColumn(name = "materipengajar_id")
	private MateriPengajar materiPengajar;

	private LocalDate periodDate;

	public LocalDate getPeriodDate() {
		return periodDate;
	}

	public void setPeriodDate(LocalDate periodDate) {
		this.periodDate = periodDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(double finalScore) {
		this.finalScore = finalScore;
	}

	public MateriPengajar getMateriPengajar() {
		return materiPengajar;
	}

	public void setMateriPengajar(MateriPengajar materiPengajar) {
		this.materiPengajar = materiPengajar;
	}

}
