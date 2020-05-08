package com.lawencon.elearning.dao.impl;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.AdminDao;
import com.lawencon.elearning.model.Admin;

@Repository
public class AdminDaoImpl extends BaseHibernate implements AdminDao {

	@Override
	public void createAdmin(Admin admin) throws Exception {
		em.persist(admin);
	}

	@Override
	public void updateAdmin(Admin admin) throws Exception {
		em.merge(admin);
	}

	@Override
	public void deleteAdmin(String id) throws Exception {
		Query q = em.createQuery(" from Admin where adminId = :idParam");
		q.setParameter("idParam", id);
		Admin temp = new Admin();
		temp = (Admin) q.getSingleResult();
		em.remove(temp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getById(String id) throws Exception {
		Query a = em.createNativeQuery("select name, username from tb_admin where adminid = :idParam");
		a.setParameter("idParam", id);
		List<Object[]> result = a.getResultList();
		return bMapperHibernate(result, "name", "username");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getAdmin() throws Exception {
		Query a = em.createNativeQuery("select name, username from tb_admin");
		List<Object[]> result = a.getResultList();
		return bMapperHibernate(result, "nama", "username");
	}
}
