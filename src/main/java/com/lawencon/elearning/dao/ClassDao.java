package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.Class;

public interface ClassDao {
	void insert(Class kelas) throws Exception;

	void update(Class kelas) throws Exception;

	void delete(String id) throws Exception;

	Class findById(String id) throws Exception;

	List<Class> findAll() throws Exception;

	Class findByCode(String code) throws Exception;

	List<Map<String, Object>> findClassesByTrainer(String trainerId) throws Exception;

}
