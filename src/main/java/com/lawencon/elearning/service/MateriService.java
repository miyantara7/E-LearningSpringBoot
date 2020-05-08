package com.lawencon.elearning.service;

import java.util.List;

import com.lawencon.elearning.model.Materi;

public interface MateriService {

	boolean insert(Materi category) throws Exception;

	Materi updateMateri(String nama) throws Exception;

	void deleteMateri(String id) throws Exception;

	List<Materi> findMateri(String pId) throws Exception;

	List<Materi> findAllMateri() throws Exception;

	Materi findById(String id) throws Exception;

}
