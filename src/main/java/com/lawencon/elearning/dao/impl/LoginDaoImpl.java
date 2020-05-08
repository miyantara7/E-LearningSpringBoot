package com.lawencon.elearning.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.LoginDao;
import com.lawencon.elearning.model.Admin;
import com.lawencon.elearning.model.Account;
import com.lawencon.elearning.model.Trainer;
import com.lawencon.elearning.model.Users;

@Repository
public class LoginDaoImpl extends BaseHibernate implements LoginDao {

	@SuppressWarnings("rawtypes")
	@Override
	public Users findUserById(String id) throws Exception {
		Query q = em.createQuery("from Users u where u.account.accountId = :id").setParameter("id", id);
		List results = q.getResultList();
		return !results.isEmpty() ? (Users) results.get(0) : null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Trainer findPengajarById(String id) throws Exception {
		Query q = em.createQuery("from Trainer p where p.account.accountId = :id").setParameter("id", id);
		List results = q.getResultList();
		return !results.isEmpty() ? (Trainer) results.get(0) : null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Admin findAdminById(String id) throws Exception {
		Query q = em.createQuery("from Admin a where a.account.accountId = :id").setParameter("id", id);
		List results = q.getResultList();
		return !results.isEmpty() ? (Admin) results.get(0) : null;
	}

	@Override
	public void saveAkun(Account akun) throws Exception {
		em.persist(akun);
	}

	@Override
	public void saveUser(Users user) throws Exception {
		em.persist(user);
	}

	@Override
	public void savePengajar(Trainer pengajar) throws Exception {
		em.persist(pengajar);
	}

	@Override
	public void saveAdmin(Admin admin) throws Exception {
		em.persist(admin);
	}

}
