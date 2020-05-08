package com.lawencon.elearning.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.ClassUsersDao;
import com.lawencon.elearning.model.Class;
import com.lawencon.elearning.model.ClassUsers;

@Repository
public class ClassUsersDaoImpl extends BaseHibernate implements ClassUsersDao {
	@SuppressWarnings("rawtypes")
	@Override
	public String findClass(String uId, String idMateriPengajar) throws Exception {
		Query q = em.createNativeQuery("select id from tb_class_users tcu where user_id = ? and materipengajar_id = ?")
				.setParameter(1, uId).setParameter(2, idMateriPengajar);
		List results = q.getResultList();
		return !results.isEmpty() ? (String) results.get(0) : null;
	}

	@Override
	public ClassUsers insertKelas(ClassUsers kelas) throws Exception {
		em.persist(kelas);
		return kelas;
	}

	@Override
	public ClassUsers findByMateriUser(String user, String materiPengajar) throws Exception {
		Query q = em.createQuery(
				"select mp.jam from ClassUsers k join k.materiPengajar mp where k.user.userId = :user and mp.id = :materi")
				.setParameter("user", user).setParameter("materi", materiPengajar);
		return (ClassUsers) q.getSingleResult();
	}

	@Override
	public String findMateriPengajarJam(String materi, String pengajar, String jam, String user) throws Exception {
		Query q = em.createQuery(
				"select k.id from ClassUsers k join k.materiPengajar mp where k.user.userId = :user and mp.materi.id = :materi and mp.trainer.trainerId = :pengajar and mp.time = :jam")
				.setParameter("user", user).setParameter("materi", materi).setParameter("pengajar", pengajar)
				.setParameter("jam", jam);
		return (String) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listKelas(String mId, String pId) throws Exception {
		Query q = em
				.createNativeQuery("select tmp.id , tp.name, tmp.time from tb_materi_pengajar tmp "
						+ "join tb_classes tp on tmp.class_id  = tp.id where tmp.trainer_id = ? and tmp.materi_id = ?")
				.setParameter(1, pId).setParameter(2, mId);
		return bMapperHibernate(q.getResultList(), "class_id", "name", "time");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String cekClass(String uId, String jam) throws Exception {
		Query q = em.createNativeQuery("select tp.time from tb_class_users tcu "
				+ "join tb_materi_pengajar tp on tcu.materipengajar_id = tp.id where tcu.user_id  = ? and tp.time = ?")
				.setParameter(1, uId).setParameter(2, jam);
		List results = q.getResultList();
		return !results.isEmpty() ? (String) results.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> viewUserClass(String id) throws Exception {
		Query q = em
				.createNativeQuery("select tu.id ,tu.name from tb_class_users tcu "
						+ "join tb_users tu on tcu.user_id = tu.id where tcu.materipengajar_id = ?")
				.setParameter(1, id);
		List<Map<String, Object>> results = q.getResultList();
		return !results.isEmpty() ? bMapperHibernate(q.getResultList(), "user_id", "name") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Class> viewAllClass() throws Exception {
		Query q = em.createQuery("from Class");
		return q.getResultList();
	}
}
