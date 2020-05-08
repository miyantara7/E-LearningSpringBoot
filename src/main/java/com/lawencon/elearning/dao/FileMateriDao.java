package com.lawencon.elearning.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import com.lawencon.elearning.model.FileMateri;

public interface FileMateriDao {

	FileMateri insertMateri(FileMateri materi) throws Exception;

	List<Map<String, Object>> findMateri(String headerId) throws Exception;

	List<Map<String, Object>> findByCategory(String category) throws Exception;

	List<Map<String, Object>> findByCategoryAndTrainer(String category, String trainer) throws Exception;

	List<FileMateri> findAll() throws Exception;

	List<String> findTopic(String materiId, String pengajarId) throws Exception;

	List<Map<String, Object>> findJumMateri(String headerId) throws Exception;

	List<String> findTopic(String kelasId, String hari, LocalDate tgl, String waktu) throws Exception;

	List<Map<String, Object>> findFilePengajar(String idPengajar) throws Exception;

	List<Map<String, Object>> findClassPengajar(String idPengajar) throws Exception;

	List<Map<String, Object>> findFileClass(String idMateriPengajar) throws Exception;

	void delete(String headerId) throws Exception;

	void updateFile(FileMateri file) throws Exception;

	FileMateri findById(String id) throws Exception;

	void deleteFileMateri(FileMateri filemateri) throws Exception;

}
