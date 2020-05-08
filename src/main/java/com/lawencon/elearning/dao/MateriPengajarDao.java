package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.MateriPengajar;

public interface MateriPengajarDao {

	MateriPengajar insert(MateriPengajar materiPengajar) throws Exception;

	MateriPengajar update(MateriPengajar materiPengajar) throws Exception;

	void delete(String id) throws Exception;

	List<MateriPengajar> findAll() throws Exception;

	MateriPengajar findById(String id) throws Exception;

	List<Map<String, Object>> findMateri(String idMateri) throws Exception;

	MateriPengajar findByMateriPengajarKelas(String materi, String pengajar, String kelas) throws Exception;

	List<Map<String, Object>> showNilaiKelas(String mpId) throws Exception;
	
	List<Map<String, Object>> findMateriPengajarByTrainer(String trainerId) throws Exception;
	
}
