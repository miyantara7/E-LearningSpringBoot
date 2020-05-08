package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.MateriPengajar;

public interface MateriPengajarService {
	MateriPengajar insert(MateriPengajar materiPengajar) throws Exception;

	MateriPengajar update(String pengajar, String materi) throws Exception;

	void delete(String id) throws Exception;

	List<MateriPengajar> findAll() throws Exception;

	MateriPengajar findById(String id) throws Exception;

	List<Map<String, Object>> findMateri(String idMateri) throws Exception;

	List<Map<String, Object>> showNilaiKelas(String mpId) throws Exception;

	List<Map<String, Object>> findMateriPengajarByTrainer(String trainerId) throws Exception;
}
