package com.lawencon.elearning.dao;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.FileUsers;

public interface FileUsersDao {

	FileUsers insert(FileUsers file) throws Exception;

	FileUsers findBySoalHeader(String user, String soalHeader) throws Exception;

	FileUsers update(FileUsers file) throws Exception;

	FileUsers findById(String id) throws Exception;

	LocalTime findTimeExam(String id) throws Exception;

	Date findDateTask(String id) throws Exception;

	void deleteFileByHeaderId(String id) throws Exception;

	List<Map<String, Object>> viewFileuser(String kelasId, String userId) throws Exception;
}
