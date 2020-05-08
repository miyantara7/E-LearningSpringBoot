package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.TrainerDao;
import com.lawencon.elearning.model.Trainer;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	private TrainerDao pengajarService;

	@Override
	public String findById(String id) throws Exception {
		return pengajarService.findById(id);
	}

	@Override
	public Trainer insertPengajar(Trainer pengajar) throws Exception {
		return pengajarService.insertPengajar(pengajar);
	}

	@Override
	public void deletePengajar(String id) throws Exception {
		pengajarService.deletePengajar(id);

	}

	@Override
	public Trainer updatePengajar(String nama, String username, String password, String email) throws Exception {
		Trainer pengajar = new Trainer();
		return pengajarService.updatePengajar(pengajar);
	}

	@Override
	public List<Map<String, Object>> findAll() throws Exception {
		return pengajarService.findAll();
	}

	@Override
	public Trainer findByIdPengajar(String id) throws Exception {
		return pengajarService.findByIdPengajar(id);
	}

	@Override
	public List<?> findMateriByTrainer(String trainerId) throws Exception {
		return pengajarService.findMateriByTrainer(trainerId);
	}

}
