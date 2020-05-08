package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.Admin;

public interface AdminDao {
	void createAdmin(Admin admin) throws Exception;

	void updateAdmin(Admin admin) throws Exception;

	void deleteAdmin(String id) throws Exception;

	List<Map<String, Object>> getById(String id) throws Exception;

	List<Map<String, Object>> getAdmin() throws Exception;
}
