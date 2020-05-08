package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.elearning.model.FileMateri;

public interface FileMateriService {
	FileMateri insertMateri(MultipartFile file, String topic, String kId, String name, String tanggal, String waktu)
			throws Exception;

	List<Map<String, Object>> findMateri(String header) throws Exception;

	List<Map<String, Object>> findByCategory(String category) throws Exception;

	List<Map<String, Object>> findByCategoryAndTrainer(String category, String trainer) throws Exception;

	List<String> findTopic(String kelasId) throws Exception;

	List<Map<String, Object>> findJumMateri(String headerId) throws Exception;

	List<Map<String, Object>> findFilePengajar(String idPengajar) throws Exception;

	List<Map<String, Object>> findClassPengajar(String idPengajar) throws Exception;

	List<Map<String, Object>> findFileClass(String idMateriPengajar) throws Exception;

	void deleteFile(String headerId) throws Exception;

	void update(String id, MultipartFile file, String judul) throws Exception;

	void deleteFileMateri(String id) throws Exception;
}
