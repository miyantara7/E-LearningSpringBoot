package com.lawencon.elearning.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawencon.elearning.dao.LoginDao;
import com.lawencon.elearning.model.Admin;
import com.lawencon.elearning.model.Account;
import com.lawencon.elearning.model.Trainer;
import com.lawencon.elearning.model.Users;
import com.lawencon.elearning.payload.request.SignupRequest;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;

	@Override
	public void saveAkun(SignupRequest signUp, Account akun) throws Exception {
		loginDao.saveAkun(akun);
		akun.getRoles().forEach(x -> {
			try {
				if (x.getName().name().equals("ROLE_USER")) {
					Users user = new Users();
					user.setName(signUp.getNama());
					user.setAccount(akun);
					loginDao.saveUser(user);

				} else if (x.getName().name().equals("ROLE_PENGAJAR")) {
					Trainer pengajar = new Trainer();
					pengajar.setName(signUp.getNama());
					pengajar.setAccount(akun);
					loginDao.savePengajar(pengajar);
				} else {
					Admin admin = new Admin();
					admin.setName(signUp.getNama());
					admin.setAkun(akun);
					System.out.println(admin.getName());
					loginDao.saveAdmin(admin);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
