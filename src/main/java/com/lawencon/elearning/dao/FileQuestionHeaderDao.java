package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.FileQuestionHeader;

public interface FileQuestionHeaderDao {
	void updateHeader(FileQuestionHeader header) throws Exception;

	FileQuestionHeader findFileSoalHeaderById(String id) throws Exception;

	FileQuestionHeader insertHeaderSoal(FileQuestionHeader filesoalheader) throws Exception;

	List<Map<String, Object>> viewSoalHeader(String kId) throws Exception;
	
	List<Map<String, Object>> viewSoalHeaderUjian(String kId) throws Exception;

	void deleteFileHeader(FileQuestionHeader filesoalheader) throws Exception;
}
