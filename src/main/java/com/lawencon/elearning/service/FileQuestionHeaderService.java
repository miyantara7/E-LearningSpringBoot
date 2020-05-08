package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.FileQuestionHeader;

public interface FileQuestionHeaderService {

	FileQuestionHeader insertHeaderSoal(FileQuestionHeader fileSoalHeader) throws Exception;

	FileQuestionHeader findFileHeaderById(String idHeader) throws Exception;

	void updateHeader(FileQuestionHeader fileSoalHeader) throws Exception;

	List<Map<String, Object>> findSoalHeader(String kId) throws Exception;

	List<Map<String, Object>> findSoalHeaderUjian(String kId) throws Exception;

	void deleteFileHeader(String id) throws Exception;

}
