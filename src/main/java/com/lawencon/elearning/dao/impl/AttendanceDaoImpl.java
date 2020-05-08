package com.lawencon.elearning.dao.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.AttendanceDao;
import com.lawencon.elearning.model.Attendance;

@Repository
public class AttendanceDaoImpl extends BaseHibernate implements AttendanceDao {

	@Override
	public Attendance insert(Attendance absen) throws Exception {
		em.persist(absen);
		return absen;
	}

	@Override
	public Attendance update(Attendance absen) throws Exception {
		em.merge(absen);
		return absen;
	}

	@Override
	public void delete(String id) throws Exception {
		Query q = em.createQuery("from Attendance where id = :id").setParameter("id", id);
		Attendance absen = (Attendance) q.getSingleResult();
		em.remove(absen);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String findByUserMateriPengajar(String user, String materiPengajarId, LocalDate date) throws Exception {
		Query q = em.createNativeQuery("select id from "
				+ "tb_attendances ta where ta.date = ? and ta.materipengajar_id = ? and ta.user_id = ?");
		q.setParameter(1, date).setParameter(2, materiPengajarId).setParameter(3, user);
		List results = q.getResultList();
		return !results.isEmpty() ? (String) results.get(0) : null;
	}

}
