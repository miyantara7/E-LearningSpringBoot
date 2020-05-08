package com.lawencon.elearning.service;

import com.lawencon.elearning.model.MateriHeader;

public interface MateriHeaderService {

	abstract MateriHeader update(String id, String tanggal, String waktu) throws Exception;

	abstract void deleteFileHeader(String headerId) throws Exception;

}
