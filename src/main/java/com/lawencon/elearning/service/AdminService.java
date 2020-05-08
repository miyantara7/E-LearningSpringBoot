package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.Admin;

public interface AdminService {
	String createAdmin(Admin admin) throws Exception;

	String updateAdmin(Admin admin) throws Exception;

	String deleteAdmin(String id) throws Exception;

	List<Map<String, Object>> getById(String id) throws Exception;

	List<Map<String, Object>> getAdmin() throws Exception;
}
