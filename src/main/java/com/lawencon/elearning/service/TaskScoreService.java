package com.lawencon.elearning.service;

public interface TaskScoreService {

	String insert(int nilai, String userId, String mpId, int keaktifan, String jenis, String tanggal, String title,
			String period) throws Exception;

	void update(String id, int nilai, String userId, String mpId, int keaktifan, String jenis, String period)
			throws Exception;

	void delete(String id, String userId, String mpId, String jenis, String period) throws Exception;
}
