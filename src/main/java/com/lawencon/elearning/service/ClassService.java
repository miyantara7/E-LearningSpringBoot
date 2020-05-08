package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.Class;

public interface ClassService {

	boolean insert(Class kelas) throws Exception;

	void update(Class kelas) throws Exception;

	void delete(String id) throws Exception;

	Class findById(String id) throws Exception;

	List<Class> findAll() throws Exception;

	List<Map<String, Object>> findClassesByTrainer(String trainerId) throws Exception;

}
