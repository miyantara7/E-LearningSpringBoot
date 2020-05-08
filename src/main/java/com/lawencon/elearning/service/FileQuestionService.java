package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.elearning.model.FileQuestion;
import com.lawencon.elearning.model.FileQuestionHeader;

public interface FileQuestionService {
	FileQuestion insert(MultipartFile file, String materiPengajar, String jenis, String startDate, String endDate,
			String start, String end, String judulSoal) throws Exception;

	List<Map<String, Object>> findById(String id) throws Exception;

	List<Map<String, Object>> findDateTask(String idMateriPengajar) throws Exception;

	FileQuestion findSoal(String idFMateriPengajar) throws Exception;

	List<Map<String, Object>> findJumSoal(String sId) throws Exception;

	List<Map<String, Object>> findDateExam(String idMateriPengajar) throws Exception;

	String updateHeader(String id, String startDate, String endDate, String startTime, String endTime) throws Exception;

	String updateSoal(String id, MultipartFile file, String judul) throws Exception;

	String updateSoalUjian(FileQuestionHeader header) throws Exception;

	void deleteFileHeader(String id) throws Exception;

	void deleteFileSoalById(String id) throws Exception;
}
