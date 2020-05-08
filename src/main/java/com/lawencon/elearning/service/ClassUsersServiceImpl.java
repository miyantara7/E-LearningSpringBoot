package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawencon.elearning.dao.ClassUsersDao;
import com.lawencon.elearning.model.Class;
import com.lawencon.elearning.model.ClassUsers;
import com.lawencon.elearning.model.MateriPengajar;
import com.lawencon.elearning.model.Users;

@Service
@Transactional
public class ClassUsersServiceImpl implements ClassUsersService {
	@Autowired
	private ClassUsersDao kelasService;

	@Override
	public boolean findClass(String uId, String idMateriPengajar) throws Exception {
		String id = null;
		try {
			id = kelasService.findClass(uId, idMateriPengajar);
			if (id != null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	@Override
	public ClassUsers insertKelas(String idMateriPengajar, String uId) throws Exception {
		String id = null;
		ClassUsers kelas = new ClassUsers();
		MateriPengajar mp = new MateriPengajar();
		Users user = new Users();
		try {
			id = kelasService.findClass(uId, idMateriPengajar);
			if (id == null) {
				mp.setId(idMateriPengajar);
				user.setId(uId);
				kelas.setUsers(user);
				kelas.setMateriPengajar(mp);
				return kelasService.insertKelas(kelas);
			}
			return null;
		} catch (Exception e) {
			throw new Exception();
		}

	}

	@Override
	public List<Map<String, Object>> listKelas(String mId, String pId) throws Exception {
		return kelasService.listKelas(mId, pId);
	}

	@Override
	public boolean cekClass(String uId, String jam) throws Exception {
		String jams = null;
		try {
			jams = kelasService.cekClass(uId, jam);
			if (jams == null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new Exception();
		}


	}

	@Override
	public List<Map<String, Object>> viewUserClass(String id) throws Exception {
		return kelasService.viewUserClass(id);
	}

	@Override
	public List<Class> viewAllClass() throws Exception {
		return kelasService.viewAllClass();
	}
}
