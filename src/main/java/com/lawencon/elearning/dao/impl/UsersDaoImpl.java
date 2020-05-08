package com.lawencon.elearning.dao.impl;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.UsersDao;
import com.lawencon.elearning.model.Users;

@Repository
public class UsersDaoImpl extends BaseHibernate implements UsersDao {

	@Override
	public void createUser(Users user) throws Exception {
		em.persist(user);
	}

	@Override
	public void updateUser(Users user) throws Exception {
		em.merge(user);
	}

	@Override
	public void deleteUser(String id) throws Exception {
		Query q = em.createQuery(" from Users where userId = :idParam");
		q.setParameter("idParam", id);
		Users temp = new Users();
		temp = (Users) q.getSingleResult();
		em.remove(temp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getById(String id) throws Exception {
		Query a = em.createNativeQuery("select name, email, username from tb_users where userid = :idParam");
		a.setParameter("idParam", id);
		List<Object[]> result = a.getResultList();
		return bMapperHibernate(result, "name", "email", "username");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getUsers() throws Exception {
		Query a = em.createNativeQuery("select name, email, username from tb_users");
		List<Object[]> result = a.getResultList();
		return bMapperHibernate(result, "name", "email", "username");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getJadwal(String kelasId, String userId) throws Exception {
		Query q = em
				.createNativeQuery("select thm.day, thm.date, thm.topic, thm.time from "
						+ "tb_header_materi thm  join tb_materi_pengajar tmp on thm.materipengajar_id = tmp.id "
						+ "join tb_class_users cu on cu.materipengajar_id = tmp.id "
						+ "where cu.user_id = ? and tmp.id = ? order by thm.date asc")
				.setParameter(1, userId).setParameter(2, kelasId);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "day", "date", "topic", "time") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getKelasUser(String userId) throws Exception {
		Query q = em.createNativeQuery("select tp.id, tm.name as materi, tcp.name from "
				+ "tb_class_users tcu join tb_materi_pengajar tp on tcu.materipengajar_id = tp.id "
				+ "join tb_materi tm on tp.materi_id = tm.id "
				+ "join tb_classes tcp on tp.class_id = tcp.id where tcu.user_id = ?").setParameter(1, userId);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "class_id", "materi", "name") : null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> showNilaiTugasUser(String mpId, String idUser) throws Exception {
		Query q = em.createNativeQuery(
				"select  tn.id, tn.title, tn.date , tn.score as score from tb_task_scores tn where tn.materipengajar_id = ? "
						+ "and tn.user_id = ?")
				.setParameter(1, mpId).setParameter(2, idUser);
		List result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "id", "title", "date", "score") : null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> showNilaiUjianUser(String mpId, String idUser) throws Exception {
		Query q = em
				.createNativeQuery("select nu.id, nu.title, nu.date ,nu.score as score from tb_exam_scores nu "
						+ "where nu.materipengajar_id = ? and nu.user_id = ?")
				.setParameter(1, mpId).setParameter(2, idUser);
		List result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "id", "title", "date", "score") : null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> getJadwalTest(String userId, String mpId) throws Exception {
		Query q = em.createNativeQuery(
				"select th.day , th.startdate, th.enddate, th.starttime , th.endtime, th.type from tb_file_question_headers th "
						+ "join tb_materi_pengajar mp on mp.id = th.materipengajar_id "
						+ "join tb_class_users cu on cu.materipengajar_id = mp.id where cu.user_id = ? and mp.id = ?")
				.setParameter(1, userId).setParameter(2, mpId);
		List result = q.getResultList();
		return !result.isEmpty()
				? bMapperHibernate(q.getResultList(), "day", "startdate", "enddate", "start_time", "end_time", "type")
				: null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getUserById(String id) throws Exception {
		Query q = em.createNativeQuery(
				"select ta.username from tb_users tu join tb_accounts ta on tu.account_id = ta.accountid where tu.id = ? ")
				.setParameter(1, id);
		List results = q.getResultList();
		return !results.isEmpty() ? (String) results.get(0) : null;
	}

}
