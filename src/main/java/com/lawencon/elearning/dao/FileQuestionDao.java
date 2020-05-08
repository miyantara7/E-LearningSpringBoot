package com.lawencon.elearning.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.lawencon.elearning.model.FileQuestion;

public interface FileQuestionDao {
	FileQuestion insert(FileQuestion file) throws Exception;

	FileQuestion findByMateriPengajar(String pengajar, String materi) throws Exception;

	FileQuestion update(FileQuestion file) throws Exception;

	List<Map<String, Object>> findById(String id) throws Exception;

	List<Map<String, Object>> findDateTask(LocalDate tanggal, String materiPengajar, LocalTime waktu) throws Exception;

	FileQuestion findSoal(String idMateriPengajar) throws Exception;

	List<Map<String, Object>> findJumSoal(String hsId) throws Exception;

	String findHeaderIdTugas(String materiPengajar, String day) throws Exception;

	String findHeaderIdUjian(String materiPengajar, String day, Date startdate, Date enddate) throws Exception;

	void updateSoal(FileQuestion soal) throws Exception;

	List<Map<String, Object>> findDateExam(LocalTime waktu, String materiPengajar, LocalDate startDate)
			throws Exception;

	FileQuestion findFileById(String id) throws Exception;

	void deleteFileSoalByHeaderId(String id) throws Exception;

	void deleteFileSoalById(FileQuestion filesoal) throws Exception;

}
