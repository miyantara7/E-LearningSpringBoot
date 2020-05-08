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
@Table(name = "tb_class_users")
public class ClassUsers {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private String id;
	@ManyToOne
	@JoinColumn(name = "materipengajar_id")
	private MateriPengajar materiPengajar;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	public Users getUsers() {
		return users;
	}

	public MateriPengajar getMateriPengajar() {
		return materiPengajar;
	}

	public void setMateriPengajar(MateriPengajar materiPengajar) {
		this.materiPengajar = materiPengajar;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
