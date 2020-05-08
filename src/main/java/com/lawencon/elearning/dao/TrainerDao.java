package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.Trainer;

public interface TrainerDao {
	String findById(String id) throws Exception;

	Trainer insertPengajar(Trainer pengajar) throws Exception;

	void deletePengajar(String id) throws Exception;

	Trainer updatePengajar(Trainer pengajar) throws Exception;

	List<Map<String, Object>> findAll() throws Exception;

	Trainer findByIdPengajar(String id) throws Exception;
	
	List<?> findMateriByTrainer(String trainerId) throws Exception;

}
