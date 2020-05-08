package com.lawencon.elearning.dao;

import java.util.List;
import com.lawencon.elearning.model.Materi;

public interface MateriDao {

	Materi insertCategory(Materi category) throws Exception;

	Materi updateCategory(Materi category) throws Exception;

	void deleteCategory(String id) throws Exception;

	List<Materi> findAll(String pId) throws Exception;

	List<Materi> findMateriUser() throws Exception;

	Materi findById(String id) throws Exception;

	Materi findByCode(String code) throws Exception;
}
