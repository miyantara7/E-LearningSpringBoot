package com.lawencon.elearning.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String id;
	private String nama;
	private String username;
	private String email;
	private List<String> roles;

	private String idUser;

	public JwtResponse(String accessToken, String id, String nama, String username, String email, List<String> roles,
			String idUser) {
		this.token = accessToken;
		this.id = id;
		this.nama = nama;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.idUser = idUser;
	}

	public JwtResponse(String accessToken, String username) {
		this.token = accessToken;
		this.username = username;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

}
