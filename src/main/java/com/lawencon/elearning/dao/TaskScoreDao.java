package com.lawencon.elearning.dao;

import com.lawencon.elearning.model.TaskScore;

public interface TaskScoreDao {

	TaskScore insert(TaskScore nilai) throws Exception;

	TaskScore findByUserAndClass(String classId, String userId) throws Exception;

	TaskScore findById(String id) throws Exception;

	TaskScore update(TaskScore nilai) throws Exception;

	void delete(TaskScore nilai) throws Exception;

}
