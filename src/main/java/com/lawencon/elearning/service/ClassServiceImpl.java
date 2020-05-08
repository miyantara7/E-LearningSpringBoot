package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.ClassDao;
import com.lawencon.elearning.model.Class;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassDao classDao;

	@Override
	public boolean insert(Class kelas) throws Exception {
		Class c = null;
		try {
			c = classDao.findByCode(kelas.getCode());
			if (c == null) {
				classDao.insert(kelas);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new Exception();
		}

	}

	@Override
	public void update(Class kelas) throws Exception {
		classDao.update(kelas);

	}

	@Override
	public void delete(String id) throws Exception {
		classDao.delete(id);

	}

	@Override
	public Class findById(String id) throws Exception {
		return classDao.findById(id);
	}

	@Override
	public List<Class> findAll() throws Exception {
		return classDao.findAll();
	}

	@Override
	public List<Map<String, Object>> findClassesByTrainer(String trainerId) throws Exception {
		return classDao.findClassesByTrainer(trainerId);
	}

}
