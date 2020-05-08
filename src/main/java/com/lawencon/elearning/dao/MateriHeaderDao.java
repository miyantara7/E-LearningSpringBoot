package com.lawencon.elearning.dao;

import java.time.LocalDate;
import java.util.List;

import com.lawencon.elearning.model.MateriHeader;

public interface MateriHeaderDao {

	String findByTopicCategoryTrainer(String topik, LocalDate tanggal, String waktu, String hari, String kId)
			throws Exception;

	List<MateriHeader> findAll() throws Exception;

	MateriHeader insertHeader(MateriHeader materiHeader) throws Exception;

	void deleteHeader(String id) throws Exception;

	MateriHeader updateHeader(MateriHeader materiHeader) throws Exception;

	MateriHeader findById(String id) throws Exception;

}
