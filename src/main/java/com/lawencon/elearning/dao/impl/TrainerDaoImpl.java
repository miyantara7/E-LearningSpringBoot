package com.lawencon.elearning.dao.impl;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.TrainerDao;
import com.lawencon.elearning.model.Trainer;

@Repository
public class TrainerDaoImpl extends BaseHibernate implements TrainerDao {

	@SuppressWarnings("rawtypes")
	@Override
	public String findById(String id) throws Exception {
		Query q = em.createNativeQuery(
				"select ta.username from tb_trainers tp join tb_accounts ta on tp.account_id = ta.accountid where tp.trainerid = ?")
				.setParameter(1, id);
		List results = q.getResultList();
		return !results.isEmpty() ? (String) results.get(0) : null;
	}

	@Override
	public Trainer insertPengajar(Trainer pengajar) throws Exception {
		em.persist(pengajar);
		return pengajar;
	}

	@Override
	public void deletePengajar(String id) throws Exception {
		Query q = em.createQuery(" from Pengajar where id = :id").setParameter("id", id);
		Trainer pengajar = (Trainer) q.getSingleResult();
		em.remove(pengajar);

	}

	@Override
	public Trainer updatePengajar(Trainer pengajar) throws Exception {
		return em.merge(pengajar);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findAll() throws Exception {
		Query q = em.createQuery("select id, name from Trainer");
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "id", "name") : null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Trainer findByIdPengajar(String id) throws Exception {
		Query q = em.createQuery("from Trainer where trainerId =: idParam").setParameter("idParam", id);
		List results = q.getResultList();
		return !results.isEmpty() ? (Trainer) results.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findMateriByTrainer(String trainerId) throws Exception {
		Query q = em.createNativeQuery(
				"select tm.\"name\" as materi , tc.\"name\" as kelas, tmp.startdate , tmp.enddate\r\n"
						+ "from tb_materi_pengajar tmp \r\n" + "join tb_classes tc on tmp.class_id = tc.id \r\n"
						+ "join tb_materi tm on tm.id = tmp.materi_id \r\n" + "where tmp.trainer_id = :trainerId")
				.setParameter("trainerId", trainerId);
		List<?> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "materi", "kelas", "startdate", "enddate")
				: null;
	}

}
