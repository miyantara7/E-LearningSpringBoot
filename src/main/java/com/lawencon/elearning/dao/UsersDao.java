package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.Users;

public interface UsersDao {
	void createUser(Users user) throws Exception;

	void updateUser(Users user) throws Exception;

	void deleteUser(String id) throws Exception;

	String getUserById(String id) throws Exception;

	List<Map<String, Object>> getById(String id) throws Exception;

	List<Map<String, Object>> getUsers() throws Exception;

	List<Map<String, Object>> getJadwal(String kelasId, String userId) throws Exception;

	List<Map<String, Object>> getKelasUser(String userId) throws Exception;

	List<Map<String, Object>> showNilaiTugasUser(String mpId, String idUser) throws Exception;

	List<Map<String, Object>> showNilaiUjianUser(String mpId, String idUser) throws Exception;

	List<Map<String, Object>> getJadwalTest(String userId, String mpId) throws Exception;
}
