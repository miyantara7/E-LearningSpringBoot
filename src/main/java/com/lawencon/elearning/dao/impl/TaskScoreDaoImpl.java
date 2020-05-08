package com.lawencon.elearning.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.TaskScoreDao;
import com.lawencon.elearning.model.TaskScore;

@Repository
public class TaskScoreDaoImpl extends BaseHibernate implements TaskScoreDao {

	@Override
	public TaskScore insert(TaskScore nilai) throws Exception {
		em.persist(nilai);
		return nilai;
	}

	@Override
	public TaskScore findByUserAndClass(String classId, String userId) throws Exception {
		Query q = em.createQuery("from TaskScore n where n.materiPengajar.id = :class and n.user.userId = :user");
		q.setParameter("class", classId).setParameter("user", userId);
		return (TaskScore) q.getSingleResult();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public TaskScore findById(String id) throws Exception {
		Query q = em.createQuery("from TaskScore where id = :id").setParameter("id", id);
		List result = q.getResultList();
		return !result.isEmpty() ? (TaskScore) q.getSingleResult() : null;
	}

	@Override
	public TaskScore update(TaskScore nilai) throws Exception {
		em.merge(nilai);
		return nilai;
	}

	@Override
	public void delete(TaskScore nilai) throws Exception {
		em.remove(nilai);

	}
}
