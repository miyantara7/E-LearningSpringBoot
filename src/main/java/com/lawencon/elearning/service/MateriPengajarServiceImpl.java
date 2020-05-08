package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.MateriPengajarDao;
import com.lawencon.elearning.model.Class;
import com.lawencon.elearning.model.Materi;
import com.lawencon.elearning.model.MateriPengajar;
import com.lawencon.elearning.model.Trainer;

@Service
@Transactional
public class MateriPengajarServiceImpl implements MateriPengajarService {

	@Autowired
	private MateriPengajarDao materiPengajarService;

	@Autowired
	private MateriService materiService;

	@Autowired
	private ClassService kelasService;

	@Override
	public MateriPengajar insert(MateriPengajar materiPengajar) throws Exception {
		Materi materi = null;
		Class kelas = null;
		try {
			try {
				materi = materiService.findById(materiPengajar.getMateri().getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				kelas = kelasService.findById(materiPengajar.getClasses().getId());
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (materi != null && kelas != null) {
				materiPengajar.setTrainer(materiPengajar.getTrainer());
				return materiPengajarService.insert(materiPengajar);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	@Override
	public MateriPengajar update(String pengajar, String materi) throws Exception {
		MateriPengajar materiPengajar = new MateriPengajar();
		Materi m = new Materi();
		m.setId(materi);
		Trainer p = new Trainer();
		p.setTrainerId(pengajar);
		materiPengajar.setMateri(m);
		materiPengajar.setTrainer(p);
		return materiPengajarService.update(materiPengajar);
	}

	@Override
	public void delete(String id) throws Exception {
		materiPengajarService.delete(id);

	}

	@Override
	public List<MateriPengajar> findAll() throws Exception {
		return materiPengajarService.findAll();
	}

	@Override
	public MateriPengajar findById(String id) throws Exception {
		return materiPengajarService.findById(id);
	}

	@Override
	public List<Map<String, Object>> findMateri(String idMateri) throws Exception {
		return materiPengajarService.findMateri(idMateri);
	}

	@Override
	public List<Map<String, Object>> showNilaiKelas(String mpId) throws Exception {
		return materiPengajarService.showNilaiKelas(mpId);
	}

	@Override
	public List<Map<String, Object>> findMateriPengajarByTrainer(String trainerId) throws Exception {
		return materiPengajarService.findMateriPengajarByTrainer(trainerId);
	}

}
