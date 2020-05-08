package com.lawencon.elearning.dao.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.dao.ReportDao;

@Repository
public class ReportDaoImpl extends BaseHibernate implements ReportDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<?> nilaiAkhirDetail(String userId, String mpId, String classId) throws Exception {
		Query q = em.createNativeQuery(
				"select tes.title as title, tu11.\"name\" as namaMurid, tm11.\"name\" as materi, tes.score as nilai, tc.\"name\" as kelas\r\n"
						+ "from tb_exam_scores  tes \r\n"
						+ "join tb_materi_pengajar tmp11 on tes.materipengajar_id = tmp11.id \r\n"
						+ "join tb_classes tc on tc.id = tmp11.class_id \r\n"
						+ "join tb_materi tm11 on tm11.id = tmp11.materi_id\r\n"
						+ "join tb_users tu11 on tu11.id = tes.user_id\r\n"
						+ "where tes.user_id = :userId and tes.materipengajar_id = "
						+ "(select tmp.id from tb_materi_pengajar tmp where tmp.materi_id = :mpId and tmp.class_id = :classId) "
						+ "union \r\n"
						+ "select tts.title as title, tu1.\"name\" as namaMurid, tm1.\"name\" as materi, tts.score as nilai, tc1.\"name\" as kelas\r\n"
						+ "from tb_task_scores tts \r\n"
						+ "join tb_materi_pengajar tmp1 on tts.materipengajar_id = tmp1.id \r\n"
						+ "join tb_classes tc1 on tc1.id = tmp1.class_id \r\n"
						+ "join tb_materi tm1 on tm1.id = tmp1.materi_id\r\n"
						+ "join tb_users tu1 on tu1.id = tts.user_id\r\n"
						+ "where tts.user_id = :userId and tts.materipengajar_id = "
						+ "(select tmp.id from tb_materi_pengajar tmp where tmp.materi_id = :mpId and tmp.class_id = :classId)");
		q.setParameter("userId", userId);
		q.setParameter("mpId", classId);
		q.setParameter("classId", mpId);
		List<?> result = q.getResultList();
		System.out.println("userId : " + userId);
		System.out.println("mpId : " + mpId);
		System.out.println("classId : " + classId);
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "title", "namamurid", "materi", "nilai", "kelas")
				: null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findAllJadwal() throws Exception {
		Query q = em.createNativeQuery("select tt.\"name\" pengajar, tc.\"name\" kelas, tm.\"name\" materi,\r\n"
				+ "tmp.\"time\" jam, to_char(tmp.startdate, 'DD-mm-yyyy') as startdate, to_char(tmp.enddate,'dd-mm-yyyy') as enddate from tb_materi_pengajar tmp\r\n"
				+ "join tb_trainers tt on tmp.trainer_id = tt.trainerid\r\n"
				+ "join tb_classes tc on tmp.class_id = tc.id \r\n" + "join tb_materi tm on tmp.materi_id = tm.id");
		List<?> result = q.getResultList();
		return !result.isEmpty()
				? bMapperHibernate(q.getResultList(), "pengajar", "kelas", "materi", "jam", "startdate", "enddate")
				: null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findAllKelas() throws Exception {
		Query q = em.createNativeQuery(
				"select tm.\"name\" as materi , tc.\"name\" as kelas , tu.\"name\" as murid, ta.email as email from tb_class_users tcu\r\n"
						+ "join tb_users tu on tu.id = tcu.user_id\r\n"
						+ "join tb_accounts ta on tu.account_id = ta.accountid \r\n"
						+ "join tb_materi_pengajar tmp on tmp.id = tcu.materipengajar_id \r\n"
						+ "join tb_materi tm on tm.id = tmp.materi_id \r\n"
						+ "join tb_classes tc on tc.id = tmp.class_id");
		List<?> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "materi", "kelas", "name", "email") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getJadwalPeriode(String tgl1, String tgl2) throws Exception {
		Query q = em.createNativeQuery(
				"select t.name as pengajar, tc.name kelas, m.name as materi, tmp.time, to_char(tmp.startdate, 'DD-mm-yyyy') as startdate, to_char(tmp.enddate, 'DD-mm-yyyy') as enddate \r\n"
						+ "from tb_materi_pengajar tmp\r\n" + "join tb_materi m on m.id = tmp.materi_id\r\n"
						+ "join tb_trainers t on t.trainerid = tmp.trainer_id\r\n"
						+ "join tb_classes tc on tmp.class_id = tc.id where tmp.startdate between date(?) and date(?)")
				.setParameter(1, tgl1).setParameter(2, tgl2);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty()
				? bMapperHibernate(q.getResultList(), "pengajar", "kelas", "materi", "jam", "startdate", "enddate")
				: null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findByClassMateri(String classId, String materiPengajarId) throws Exception {
		Query q = em.createNativeQuery(
				"select thm.topic pelajaran, to_char(thm.\"date\" , 'DD Mon yyyy') as tanggal, thm.\"time\" waktu "
						+ "from tb_header_materi thm "
						+ "join tb_materi_pengajar tmp on thm.materipengajar_id = tmp.id "
						+ "where tmp.class_id = ? and tmp.materi_id = ? " + "union "
						+ "select tf.title pelajaran, to_char(tf.startdate, 'DD Mon yyyy') tanggal, to_char(tf.starttime , 'HH24:MI') as waktu "
						+ "from tb_file_question_headers tf "
						+ "join tb_materi_pengajar tmp on tf.materipengajar_id = tmp.id "
						+ "where tmp.class_id = ? and tmp.materi_id = ?")
				.setParameter(1, classId).setParameter(2, materiPengajarId).setParameter(3, classId)
				.setParameter(4, materiPengajarId);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "pelajaran", "tanggal", "waktu") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findAllMapel() throws Exception {
		Query q = em.createNativeQuery(
				"select thm.topic pelajaran, to_char(thm.\"date\", 'DD Mon yyyy') tanggal, thm.\"time\" waktu\r\n"
						+ "from tb_header_materi thm\r\n"
						+ "join tb_materi_pengajar tmp on thm.materipengajar_id = tmp.id \r\n" + "union\r\n"
						+ "select tf.title pelajaran, to_char(tf.startdate, 'DD Mon yyyy') tanggal, to_char(tf.starttime , 'HH12:MI') as waktu\r\n"
						+ "from tb_file_question_headers tf\r\n"
						+ "join tb_materi_pengajar tmp on tf.materipengajar_id = tmp.id");
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "pelajaran", "tanggal", "waktu") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> nilaiAkhir(String userId) throws Exception {
		Query q = em.createNativeQuery(
				"select tu.\"name\" as nama, tna.finalscore as nilai, tm.\"name\" as materi, tp.\"name\" as pengajar, tc.\"name\" as kelas\r\n"
						+ "from tb_final_scores tna\r\n" + "join tb_users tu on tu.id = tna.user_id \r\n"
						+ "join tb_materi_pengajar tmp on tna.materipengajar_id = tmp.id \r\n"
						+ "join tb_classes tc on tc.id = tmp.class_id \r\n"
						+ "join tb_materi tm on tm.id = tmp.materi_id \r\n"
						+ "join tb_trainers tp on tp.trainerid = tmp.trainer_id \r\n" + "where tna.user_id = :userId");
		q.setParameter("userId", userId);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "nama", "nilai", "materi", "pengajar", "kelas")
				: null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findPesertaClassByMateriClass(String materiId, String classId) throws Exception {
		Query q = em.createNativeQuery(
				"select u.\"name\", a.email from tb_class_users tcu\r\n" + "join tb_users u on u.id = tcu.user_id \r\n"
						+ "join tb_accounts a on a.accountid = u.account_id \r\n" + "where tcu.materipengajar_id = "
						+ "(select tmp.id from tb_materi_pengajar tmp where materi_id = ? and  class_id = ?)");
		q.setParameter(1, materiId);
		q.setParameter(2, classId);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "name", "email") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findMateriByUser(String userId) throws Exception {
		Query q = em.createNativeQuery("select tm.id , tm.\"name\" materi \r\n" + "from tb_class_users tcu \r\n"
				+ "join tb_materi_pengajar tmp on tcu.materipengajar_id = tmp.id \r\n"
				+ "join tb_materi tm on tm.id = tmp.materi_id \r\n" + "join tb_users tu on tu.id = tcu.user_id \r\n"
				+ "where tcu.user_id = :userId");
		q.setParameter("userId", userId);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "id", "materi") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findKelasByUser(String userId) throws Exception {
		Query q = em.createNativeQuery("select tc.id , tc.\"name\" as kelas\r\n" + "from tb_class_users tcu \r\n"
				+ "join tb_users tu on tu.id = tcu.user_id \r\n"
				+ "join tb_materi_pengajar tmp on tcu.materipengajar_id =tmp.id \r\n"
				+ "join tb_classes tc on tc.id =tmp.class_id \r\n" + "where tcu.user_id = :userId");
		q.setParameter("userId", userId);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "id", "kelas") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> nilaiAkhirDetailByUser(String userId) throws Exception {
		Query q = em.createNativeQuery(
				"select tes.title as title, tu11.\"name\" as namaMurid, tm11.\"name\" as materi, tes.score as nilai, tc.\"name\" as kelas\r\n"
						+ "from tb_exam_scores  tes \r\n"
						+ "join tb_materi_pengajar tmp11 on tes.materipengajar_id = tmp11.id \r\n"
						+ "join tb_classes tc on tc.id = tmp11.class_id \r\n"
						+ "join tb_materi tm11 on tm11.id = tmp11.materi_id\r\n"
						+ "join tb_users tu11 on tu11.id = tes.user_id\r\n" + "where tes.user_id = :userId "
						+ "union \r\n"
						+ "select tts.title as title, tu1.\"name\" as namaMurid, tm1.\"name\" as materi, tts.score as nilai, tc1.\"name\" as kelas\r\n"
						+ "from tb_task_scores tts \r\n"
						+ "join tb_materi_pengajar tmp1 on tts.materipengajar_id = tmp1.id \r\n"
						+ "join tb_classes tc1 on tc1.id = tmp1.class_id \r\n"
						+ "join tb_materi tm1 on tm1.id = tmp1.materi_id\r\n"
						+ "join tb_users tu1 on tu1.id = tts.user_id\r\n" + "where tts.user_id = :userId");
		q.setParameter("userId", userId);
		List<?> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "title", "namamurid", "materi", "nilai", "kelas")
				: null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> nilaiPesertaByMPClass(String materiId, String classId) throws Exception {
		Query q = em.createNativeQuery("select u.\"name\" , a.email, tfs.finalscore from tb_final_scores tfs \r\n"
				+ "join tb_users u on u.id = tfs.user_id \r\n" + "join tb_accounts a on a.accountid = u.account_id \r\n"
				+ "where tfs.materipengajar_id = \r\n"
				+ "(select tmp.id from tb_materi_pengajar tmp where materi_id = :materiId and  class_id = :classId);");
		q.setParameter("materiId", materiId);
		q.setParameter("classId", classId);
		List<?> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "name", "email", "finalscore") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> rincianNilai(String materiId, String classId, String period) throws Exception {
		Query q = em.createNativeQuery(
				"select tu.\"name\", cast(sum(ts.score)/(select count(tf.\"type\") from tb_file_question_headers tf \r\n"
						+ "where tf.\"type\" = 'TUGAS' and tf.materipengajar_id = \r\n"
						+ "(select tmp.id from tb_materi_pengajar tmp \r\n" + "where materi_id = :materiId and  \r\n"
						+ "class_id = :classId)) as int) as rata2_tugas,\r\n"
						+ "coalesce(cast(ex.score/(select count(tf.\"type\") from tb_file_question_headers tf \r\n"
						+ "where tf.\"type\" = 'UJIAN' and tf.materipengajar_id = \r\n"
						+ "(select tmp.id from tb_materi_pengajar tmp \r\n" + "where materi_id = :materiId and  \r\n"
						+ "class_id = :classId))as int),0) as rata2_ujian,\r\n" + "fn.finalscore from tb_users tu \r\n"
						+ "left join tb_task_scores ts on ts.user_id = tu.id\r\n"
						+ "left join(select tu.id, tu.\"name\" , sum(te.score) as score from tb_users tu \r\n"
						+ "left join tb_exam_scores te on tu.id = te.user_id \r\n"
						+ "where te.materipengajar_id = (select tmp.id from tb_materi_pengajar tmp \r\n"
						+ "where materi_id = :materiId and  class_id = :classId)\r\n"
						+ "group by tu.id ,tu.\"name\") ex on ex.id = tu.id \r\n"
						+ "left join tb_final_scores fn on fn.user_id = tu.id\r\n"
						+ "where ts.materipengajar_id = (select tmp.id from tb_materi_pengajar tmp \r\n"
						+ "where materi_id = :materiId and  class_id = :classId)\r\n"
						+ "and fn.perioddate = :period\r\n" + "group by tu.\"name\", ex.score, fn.finalscore ;");
		q.setParameter("materiId", materiId);
		q.setParameter("classId", classId);
		q.setParameter("period", LocalDate.parse(period));
		List<?> result = q.getResultList();
		return !result.isEmpty()
				? bMapperHibernate(q.getResultList(), "name", "rata2_tugas", "rata2_ujian", "finalscore")
				: null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findKehadiran(String materiId, String classId) throws Exception {
		Query q = em.createNativeQuery(
				"select tu.\"name\", cast(ab.absen as int) as kehadiran,cast(h.tgl-ab.absen as int) as absen from tb_class_users tcu \r\n"
						+ "left join tb_users tu on tcu.user_id = tu.id \r\n"
						+ "left join (select tu.id , tu.\"name\", count(ta.user_id) as absen from tb_attendances ta \r\n"
						+ "join tb_users tu on tu.id = ta.user_id \r\n"
						+ "where ta.materipengajar_id = (select tmp.id from tb_materi_pengajar tmp \r\n"
						+ "where materi_id = :materiId and  class_id = :classId)\r\n"
						+ "group by tu.id , tu.\"name\") ab on ab.id = tu.id\r\n"
						+ "left join(select tu.id, tu.\"name\", count(thm.\"date\") as tgl from tb_header_materi thm \r\n"
						+ "left join tb_class_users cu on cu.materipengajar_id = thm.materipengajar_id \r\n"
						+ "left join tb_users tu on tu.id = cu.user_id \r\n"
						+ "where cu.materipengajar_id = (select tmp.id from tb_materi_pengajar tmp \r\n"
						+ "where materi_id = :materiId and  class_id = :classId)\r\n"
						+ "group by tu.id, tu.\"name\") h on h.id = tu.id\r\n"
						+ "where tcu.materipengajar_id = (select tmp.id from tb_materi_pengajar tmp \r\n"
						+ "where materi_id = :materiId and  class_id = :classId);");
		q.setParameter("materiId", materiId);
		q.setParameter("classId", classId);
		List<?> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "name", "kehadiran", "absen") : null;
	}

}
