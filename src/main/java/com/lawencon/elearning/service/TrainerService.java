package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.Trainer;

public interface TrainerService {

	String findById(String id) throws Exception;

	Trainer findByIdPengajar(String id) throws Exception;

	Trainer insertPengajar(Trainer pengajar) throws Exception;

	void deletePengajar(String id) throws Exception;

	Trainer updatePengajar(String nama, String username, String password, String email) throws Exception;

	List<Map<String, Object>> findAll() throws Exception;

	List<?> findMateriByTrainer(String trainerId) throws Exception;
}
