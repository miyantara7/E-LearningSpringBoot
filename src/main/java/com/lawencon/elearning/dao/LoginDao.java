package com.lawencon.elearning.dao;

import com.lawencon.elearning.model.Admin;
import com.lawencon.elearning.model.Account;
import com.lawencon.elearning.model.Trainer;
import com.lawencon.elearning.model.Users;

public interface LoginDao {
	Users findUserById(String id) throws Exception;

	Trainer findPengajarById(String id) throws Exception;

	Admin findAdminById(String id) throws Exception;

	void saveAkun(Account akun) throws Exception;

	void saveUser(Users user) throws Exception;

	void savePengajar(Trainer pengajar) throws Exception;

	void saveAdmin(Admin admin) throws Exception;

}
