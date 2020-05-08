package com.lawencon.elearning.dao.impl;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.FinalScoreDao;
import com.lawencon.elearning.model.JenisFile;
import com.lawencon.elearning.model.FinalScore;

@Repository
public class FinalScoreDaoImpl extends BaseHibernate implements FinalScoreDao {

	@Override
	public int getNilaiUjianByUser(String idUser, String mpId) throws Exception {
		Query q = em.createNativeQuery("select coalesce(sum(tn.score),0) from tb_exam_scores tn "
				+ "where tn.user_id = ? and tn.materipengajar_id =?");
		q.setParameter(1, idUser).setParameter(2, mpId);
		return ((BigInteger) q.getSingleResult()).intValue();
	}

	@Override
	public Long getNilaiTugasByUser(String idUser, String mpId) throws Exception {
		Query q = em.createQuery("select coalesce(sum(tn.score),0) as score from TaskScore tn "
				+ " where tn.user.id = :user and tn.materiPengajar.id = :mpId");
		q.setParameter("user", idUser).setParameter("mpId", mpId);
		return (Long) q.getSingleResult();
	}

	@Override
	public int getCountSoalTugas(String mpId) throws Exception {
		Query q = em.createNativeQuery(
				"select count(th.type) from tb_file_question_headers th where th.materipengajar_id = ? and th.type = ?")
				.setParameter(1, mpId).setParameter(2, JenisFile.TUGAS.name());
		return ((BigInteger) q.getSingleResult()).intValue();
	}

	@Override
	public void insert(FinalScore nilaiTotal) throws Exception {
		em.persist(nilaiTotal);
	}

	@Override
	public void update(FinalScore nilaiTotal) throws Exception {
		em.merge(nilaiTotal);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public FinalScore checkNilai(String idUser, String mpId) throws Exception {
		Query q = em.createQuery("from FinalScore where materiPengajar.id = :mpId and user.id = :idUser")
				.setParameter("mpId", mpId).setParameter("idUser", idUser);
		List result = q.getResultList();
		return !result.isEmpty() ? (FinalScore) q.getResultList().get(0) : null;
	}

	@Override
	public int getCountSoalUjian(String mpId) throws Exception {
		Query q = em.createNativeQuery(
				"select count(th.type) from tb_file_question_headers th where th.materipengajar_id = ? and th.type = ?")
				.setParameter(1, mpId).setParameter(2, JenisFile.UJIAN.name());
		return ((BigInteger) q.getSingleResult()).intValue();
	}

	@Override
	public Double checkNilaibyMId(String idUser, String mId, String cId) throws Exception {
		Query q = em.createNativeQuery("select tfs.finalscore \r\n" + "from tb_final_scores tfs \r\n"
				+ "where tfs.user_id = :idUser \r\n"
				+ "and tfs.materipengajar_id = (select tmp.id from tb_materi_pengajar tmp where tmp.class_id = :cId and tmp.materi_id = :mId )");
		q.setParameter("idUser", idUser);
		q.setParameter("cId", cId);
		q.setParameter("mId", mId);
		List<?> result = q.getResultList();
		return !result.isEmpty() ? (Double) result.get(0) : null;
	}

}
