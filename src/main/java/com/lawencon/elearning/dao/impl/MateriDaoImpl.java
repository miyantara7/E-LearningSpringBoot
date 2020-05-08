package com.lawencon.elearning.dao.impl;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.MateriDao;
import com.lawencon.elearning.model.Materi;

@Repository
public class MateriDaoImpl extends BaseHibernate implements MateriDao {

	@Override
	public Materi insertCategory(Materi category) throws Exception {
		em.persist(category);
		return category;
	}

	@Override
	public Materi updateCategory(Materi category) throws Exception {
		em.merge(category);
		return category;
	}

	@Override
	public void deleteCategory(String id) throws Exception {
		Query q = em.createQuery(" from Materi where id = :id").setParameter("id", id);
		Materi cat = (Materi) q.getSingleResult();
		em.remove(cat);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Materi> findAll(String pId) throws Exception {
		Query q = em.createNativeQuery(
				"select tm.id, tm.name from tb_materi_pengajar tmp join tb_materi tm on tmp.materi_id = tm.id where trainer_id = ?")
				.setParameter(1, pId);
		return bMapperHibernate(q.getResultList(), "id", "name");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Materi> findMateriUser() throws Exception {
		Query q = em.createQuery("from Materi");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Materi findById(String id) throws Exception {
		Query q = em.createQuery("from Materi where id = :id").setParameter("id", id);
		List<Materi> results = q.getResultList();
		return !results.isEmpty() ? (Materi) results.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Materi findByCode(String code) throws Exception {
		Query q = em.createQuery("from Materi where code = :code").setParameter("code", code);
		List<Materi> results = q.getResultList();
		return !results.isEmpty() ? (Materi) results.get(0) : null;
	}

}
