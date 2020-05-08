package com.lawencon.elearning.dao.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.FileMateriDao;
import com.lawencon.elearning.model.FileMateri;

@Repository("materi_repo_hibernate")
public class FileMateriDaoImpl extends BaseHibernate implements FileMateriDao {

	@Override
	public FileMateri insertMateri(FileMateri materi) throws Exception {
		em.persist(materi);
		return materi;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findMateri(String headerId) throws Exception {
		Query q = em.createQuery("select fm.title, fm.fileMateri, fm.typeFile from FileMateri fm where fm.id = :id");
		q.setParameter("id", headerId);
		List<Object[]> result = q.getResultList();
		return bMapperHibernate(result, "title", "file", "type");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findJumMateri(String headerId) throws Exception {
		Query q = em.createNativeQuery(
				"select fm.id, fm.title, hm.topic from tb_file_materi fm join tb_header_materi hm on fm.header_id = hm.headerid where fm.header_id = ?");
		q.setParameter(1, headerId);
		return bMapperHibernate(q.getResultList(), "file_id", "title", "topic");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findByCategory(String category) throws Exception {
		Query q = em.createQuery(
				"select m.title as title, m.fileMateri as file, m.typeFile as type from FileMateri m left join m.materi as c where c.id = :category");
		q.setParameter("category", category);
		List<Object[]> result = q.getResultList();
		return bMapperHibernate(result, "title", "file", "type");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findByCategoryAndTrainer(String category, String trainer) throws Exception {
		Query q = em.createQuery(
				"select m.title as title, m.fileMateri as file, m.typeFile as type from FileMateri m left join m.materi as c "
						+ "left outer join m.trainer as trainer where c.id =: category  and trainer.trainerId =: pengajar ");
		q.setParameter("category", category);
		q.setParameter("pengajar", trainer);
		List<Object[]> result = q.getResultList();
		return bMapperHibernate(result, "title", "file", "type");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findTopic(String materiId, String pengajarId) throws Exception {
		Query q = em
				.createNativeQuery(
						"select headerid,topic, day from tb_header_materi where materi_id = ? and trainer_id = ?")
				.setParameter(1, materiId).setParameter(2, pengajarId);
		return bMapperHibernate(q.getResultList(), "headerid", "topic", "day");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FileMateri> findAll() throws Exception {
		Query q = em.createQuery(" from MateriHeader");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findTopic(String kelasId, String hari, LocalDate tgl, String waktu) throws Exception {
		Query q = em.createNativeQuery(
				"select headerid, topic , thm.day, thm.time waktu  from tb_header_materi thm join tb_materi_pengajar tp on thm.materipengajar_id = tp.id where materipengajar_id = ? and thm.day = ? and date = ? and thm.time <= ?")
				.setParameter(1, kelasId).setParameter(2, hari).setParameter(3, tgl).setParameter(4, waktu);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "headerid", "topic", "day", "time") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findFilePengajar(String idPengajar) throws Exception {
		Query q = em
				.createNativeQuery(
						"select  tfm.id, m.name ,thm.topic, cp.name, thm.day, thm.date from tb_header_materi thm \n"
								+ "join tb_file_materi tfm on thm.headerid = tfm.header_id \n"
								+ "join tb_materi_pengajar mp on mp.id = thm.materipengajar_id \n"
								+ "join tb_materi m on mp.materi_id = m.id \n"
								+ "join tb_classes cp on mp.class_id = cp.id where mp.trainer_id = ?")
				.setParameter(1, idPengajar);

		return bMapperHibernate(q.getResultList(), "id", "materi", "topic", "class", "day", "date");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findClassPengajar(String idPengajar) throws Exception {
		Query q = em
				.createNativeQuery(
						"select tmp.id, k.name kelas, m.name materi, tmp.startdate from tb_materi_pengajar tmp\n"
								+ "join tb_materi m on tmp.materi_id = m.id \n"
								+ "join tb_classes k on k.id = tmp.class_id where tmp.trainer_id =?")
				.setParameter(1, idPengajar);
		return bMapperHibernate(q.getResultList(), "kId", "class", "materi", "tanggalPeriode");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findFileClass(String idMateriPengajar) throws Exception {
		Query q = em
				.createNativeQuery(
						"select headerid, topic, day, date, time from tb_header_materi where  materipengajar_id = ?")
				.setParameter(1, idMateriPengajar);
		List<Map<String, Object>> result = q.getResultList();
		return !result.isEmpty() ? bMapperHibernate(q.getResultList(), "id", "topic", "day", "date", "time") : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(String headerId) throws Exception {
		Query q = em.createQuery("from FileMateri f where f.header.headerId = :id").setParameter("id", headerId);
		List<FileMateri> file = q.getResultList();
		file.forEach(x -> {
			em.remove(x);
		});

	}

	@Override
	public void updateFile(FileMateri file) throws Exception {
		em.merge(file);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public FileMateri findById(String id) throws Exception {
		Query q = em.createQuery("from FileMateri where id = :id").setParameter("id", id);
		List results = q.getResultList();
		return !results.isEmpty() ? (FileMateri) results.get(0) : null;
	}

	@Override
	public void deleteFileMateri(FileMateri filemateri) throws Exception {
		em.remove(filemateri);
	}

}
