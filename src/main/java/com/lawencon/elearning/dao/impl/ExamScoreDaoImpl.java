package com.lawencon.elearning.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.ExamScoreDao;
import com.lawencon.elearning.model.ExamScore;
@Repository
public class ExamScoreDaoImpl extends BaseHibernate implements ExamScoreDao {

	@Override
	public ExamScore insert(ExamScore nilai) throws Exception {
		em.persist(nilai);
		return nilai;
	}

	@Override
	public ExamScore update(ExamScore nilai) throws Exception {
		em.merge(nilai);
		return nilai;
	}

	@Override
	public ExamScore findByUserPengajarMateri(String user, String kelasId) throws Exception {
		Query q = em.createQuery(
				"from ExamScore n where n.user.userId = :user and n.materiPengajar.id = :mp");
		q.setParameter("user", user).setParameter("mp", kelasId);
		return (ExamScore) q.getSingleResult();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ExamScore findById(String id) throws Exception {
		Query q = em.createQuery("from ExamScore where id =: idParam")
				.setParameter("idParam", id);
		List result = q.getResultList();
		return !result.isEmpty() ? (ExamScore) q.getResultList().get(0) : null;
	}

	@Override
	public void delete(ExamScore nilai) throws Exception {
		em.remove(nilai);
		
	}

}
