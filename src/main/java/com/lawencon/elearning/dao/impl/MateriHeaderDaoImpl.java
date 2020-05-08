package com.lawencon.elearning.dao.impl;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.MateriHeaderDao;
import com.lawencon.elearning.model.MateriHeader;

@Repository
public class MateriHeaderDaoImpl extends BaseHibernate implements MateriHeaderDao {

	@Override
	public String findByTopicCategoryTrainer(String topik, LocalDate tanggal, String waktu, String hari, String kId)
			throws Exception {
		Query q = em.createNativeQuery("select thm.headerid from tb_header_materi thm \n"
				+ "where thm.topic = ? and thm.date = ? and thm.time = ? and thm.day = ? and thm.materipengajar_id = ?")
				.setParameter(1, topik).setParameter(2, tanggal).setParameter(3, waktu).setParameter(4, hari)
				.setParameter(5, kId);
		return (String) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MateriHeader> findAll() throws Exception {
		Query q = em.createQuery(" from MateriHeader");
		return q.getResultList();
	}

	@Override
	public MateriHeader insertHeader(MateriHeader materiHeader) throws Exception {
		em.persist(materiHeader);
		return materiHeader;
	}

	@Override
	public void deleteHeader(String id) throws Exception {
		Query q = em.createQuery(" from MateriHeader where headerId = :id").setParameter("id", id);
		MateriHeader h = (MateriHeader) q.getSingleResult();
		em.remove(h);

	}

	@Override
	public MateriHeader updateHeader(MateriHeader materiHeader) throws Exception {
		em.merge(materiHeader);
		return materiHeader;
	}

	@Override
	public MateriHeader findById(String id) throws Exception {
		Query q = em.createQuery("from MateriHeader where id = :id").setParameter("id", id);
		return (MateriHeader) q.getSingleResult();
	}

}
