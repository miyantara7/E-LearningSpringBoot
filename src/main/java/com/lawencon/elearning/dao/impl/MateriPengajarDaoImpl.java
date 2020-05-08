package com.lawencon.elearning.dao.impl;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.MateriPengajarDao;
import com.lawencon.elearning.model.MateriPengajar;

@Repository
public class MateriPengajarDaoImpl extends BaseHibernate implements MateriPengajarDao {

	@Override
	public MateriPengajar insert(MateriPengajar materiPengajar) throws Exception {
		em.persist(materiPengajar);
		return materiPengajar;
	}

	@Override
	public MateriPengajar update(MateriPengajar materiPengajar) throws Exception {
		em.merge(materiPengajar);
		return materiPengajar;
	}

	@Override
	public void delete(String id) throws Exception {
		Query q = em.createQuery(" from MateriPengajar where id = :id").setParameter("id", id);
		MateriPengajar mp = (MateriPengajar) q.getSingleResult();
		em.remove(mp);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MateriPengajar> findAll() throws Exception {
		Query q = em.createQuery(" from MateriPelajar");
		return q.getResultList();
	}

	@Override
	public MateriPengajar findById(String id) throws Exception {
		Query q = em.createQuery(" from MateriPengajar where id = :id").setParameter("id", id);
		MateriPengajar mp = (MateriPengajar) q.getSingleResult();
		return mp;
	}

	@Override
	public MateriPengajar findByMateriPengajarKelas(String materi, String pengajar, String kelas) throws Exception {
		Query q = em.createQuery(
				"from MateriPengajar m where m.classes.id = :kelas and m.materi.id = :materi and m.trainer.trainerId = :pengajar")
				.setParameter("kelas", kelas).setParameter("pengajar", pengajar).setParameter("materi", materi);
		return (MateriPengajar) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findMateri(String idMateri) throws Exception {
		Query q = em.createNativeQuery(
				"select tmp.id , tmp.materi_id, tp.name nama , tcp.name kelas , tmp.time, tmp.startdate, tmp.enddate "
						+ "from tb_materi_pengajar tmp join tb_trainers tp on tmp.trainer_id = tp.trainerid join "
						+ "tb_classes tcp on tcp.id = tmp.class_id where tmp.materi_id = ?")
				.setParameter(1, idMateri);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty()
				? bMapperHibernate(q.getResultList(), "kelas_id", "materi_id", "nama", "class", "time", "start_Date",
						"end_Date")
				: null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> showNilaiKelas(String mpId) throws Exception {
		Query q = em.createNativeQuery(
				"select u.name, round(cast(tn.finalscore as numeric),2) as finalscore from tb_final_scores tn "
						+ "join tb_users u on u.id = tn.user_id where tn.materipengajar_id = ? order by tn.finalscore desc")
				.setParameter(1, mpId);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "name", "final_score") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findMateriPengajarByTrainer(String trainerId) throws Exception {
		Query q = em
				.createNativeQuery("select m.id , m.name from tb_materi_pengajar tmp \n"
						+ "join tb_materi m on m.id = tmp.materi_id where tmp.trainer_id = ?")
				.setParameter(1, trainerId);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "materi_id", "materi") : null;
	}

}
