package com.lawencon.elearning.service;

public interface ExamScoreService {
	String insert(int nilai, String user, String mpId, String tanggal, String title, String period) throws Exception;

	void update(String id, int nilai, String user, String mpId, String jenis, String period) throws Exception;

	void delete(String id, String user, String mpId, String jenis, String period) throws Exception;
}
