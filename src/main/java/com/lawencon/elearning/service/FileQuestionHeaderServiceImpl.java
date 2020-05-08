package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.FileQuestionHeaderDao;
import com.lawencon.elearning.model.FileQuestionHeader;

@Service
@Transactional
public class FileQuestionHeaderServiceImpl implements FileQuestionHeaderService {
	
	@Autowired
	private FileQuestionHeaderDao fileHeaderDao;

	@Override
	public FileQuestionHeader insertHeaderSoal(FileQuestionHeader fileSoalHeader) throws Exception {
		return fileHeaderDao.insertHeaderSoal(fileSoalHeader);
	}

	@Override
	public FileQuestionHeader findFileHeaderById(String idHeader) throws Exception {
		return fileHeaderDao.findFileSoalHeaderById(idHeader);
	}

	@Override
	public void updateHeader(FileQuestionHeader fileSoalHeader) throws Exception {
		fileHeaderDao.updateHeader(fileSoalHeader);
	}

	@Override
	public List<Map<String, Object>> findSoalHeader(String kId) throws Exception {
		return fileHeaderDao.viewSoalHeader(kId);
	}

	@Override
	public void deleteFileHeader(String id) throws Exception {
		FileQuestionHeader fileHeader = null;
		try {
			fileHeader = fileHeaderDao.findFileSoalHeaderById(id);
			if(fileHeader != null) fileHeaderDao.deleteFileHeader(fileHeader);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public List<Map<String, Object>> findSoalHeaderUjian(String kId) throws Exception {
		return fileHeaderDao.viewSoalHeaderUjian(kId);
	}

	
}
