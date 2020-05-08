package com.lawencon.elearning.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.MateriDao;
import com.lawencon.elearning.model.Materi;

@Service
@Transactional
public class MateriServiceImpl implements MateriService {

	@Autowired
	private MateriDao categoriesService;

	@Override
	public boolean insert(Materi materi) throws Exception {
		Materi cekMateri = null;
		try {
			cekMateri = categoriesService.findByCode(materi.getCode());
			if (cekMateri == null) {
				categoriesService.insertCategory(materi);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new Exception();
		}

	}

	@Override
	public Materi updateMateri(String nama) throws Exception {
		Materi materi = new Materi();
		materi.setName(nama);
		return categoriesService.updateCategory(materi);
	}

	@Override
	public void deleteMateri(String id) throws Exception {
		categoriesService.deleteCategory(id);

	}

	@Override
	public List<Materi> findMateri(String pId) throws Exception {
		return categoriesService.findAll(pId);
	}

	@Override
	public List<Materi> findAllMateri() throws Exception {
		return categoriesService.findMateriUser();
	}

	@Override
	public Materi findById(String id) throws Exception {
		return categoriesService.findById(id);
	}

}
