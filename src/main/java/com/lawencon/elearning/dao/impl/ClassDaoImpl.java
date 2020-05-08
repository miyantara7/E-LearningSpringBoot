package com.lawencon.elearning.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.ClassDao;
import com.lawencon.elearning.model.Class;

@Repository
public class ClassDaoImpl extends BaseHibernate implements ClassDao {

	@Override
	public void insert(Class kelas) throws Exception {
		em.persist(kelas);
	}

	@Override
	public void update(Class kelas) throws Exception {
		em.merge(kelas);
	}

	@Override
	public void delete(String id) throws Exception {
		Query q = em.createQuery("from Class where id = :id").setParameter("id", id);
		Class kelas = (Class) q.getSingleResult();
		em.remove(kelas);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class findById(String id) throws Exception {
		Query q = em.createQuery("from Class where id = :id").setParameter("id", id);
		List results = q.getResultList();
		return !results.isEmpty() ? (Class) results.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Class> findAll() throws Exception {
		Query q = em.createQuery("from Class");
		return q.getResultList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class findByCode(String code) throws Exception {
		Query q = em.createQuery("from Class where code = :code").setParameter("code", code);
		List results = q.getResultList();
		return !results.isEmpty() ? (Class) results.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findClassesByTrainer(String trainerId) throws Exception {
		Query q = em
				.createNativeQuery("select tc.id , tc.name from tb_classes tc "
						+ "join tb_materi_pengajar mp on mp.class_id = tc.id where mp.trainer_id = ?")
				.setParameter(1, trainerId);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "class_id", "materi_id") : null;
	}

}
