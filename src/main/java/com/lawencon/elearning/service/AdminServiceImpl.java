package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.elearning.dao.AdminDao;
import com.lawencon.elearning.model.Admin;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public String createAdmin(Admin admin) throws Exception {
		adminDao.createAdmin(admin);
		return "Berhasil Tambah Admin";
	}

	@Override
	public String updateAdmin(Admin admin) throws Exception {
		adminDao.updateAdmin(admin);
		return "Berhasil Update Admin";
	}

	@Override
	public String deleteAdmin(String id) throws Exception {
		adminDao.deleteAdmin(id);
		return "Berhasil Delete Admin";
	}

	@Override
	public List<Map<String, Object>> getById(String id) throws Exception {
		return adminDao.getById(id);
	}

	@Override
	public List<Map<String, Object>> getAdmin() throws Exception {
		return adminDao.getAdmin();
	}

}
