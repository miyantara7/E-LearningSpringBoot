package com.lawencon.elearning.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lawencon.elearning.model.Account;
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String akunId;

	private String username;
	
	private static String nama;
	
	private String email;
	
	private static String idUser;
	
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String akunId, String email, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.akunId = akunId;
		this.email = email;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Account akun, String test, String id) {
		List<GrantedAuthority> authorities = akun.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
		nama = test;
		idUser = id;
		return new UserDetailsImpl(
				akun.getAccountId(), 
				akun.getEmail(),
				akun.getUsername(), 
				akun.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getAkunId() {
		return akunId;
	}
	

	public String getNama() {
		return nama;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(akunId, user.akunId);
	}

	@SuppressWarnings("static-access")
	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getIdUser() {
		return idUser;
	}
	
	
	
}
