package com.lawencon.elearning.dao;

import com.lawencon.elearning.model.ExamScore;

public interface ExamScoreDao {

	ExamScore insert(ExamScore nilai) throws Exception;

	ExamScore update(ExamScore nilai) throws Exception;

	ExamScore findByUserPengajarMateri(String user, String materiPengajarId) throws Exception;

	ExamScore findById(String id) throws Exception;

	void delete(ExamScore nilai) throws Exception;
}
