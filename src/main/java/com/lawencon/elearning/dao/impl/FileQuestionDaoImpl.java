package com.lawencon.elearning.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.FileQuestionDao;
import com.lawencon.elearning.model.FileQuestion;

@Repository
public class FileQuestionDaoImpl extends BaseHibernate implements FileQuestionDao {

	@Override
	public FileQuestion insert(FileQuestion file) throws Exception {
		em.persist(file);
		return file;
	}

	@Override
	public FileQuestion findByMateriPengajar(String pengajar, String materi) throws Exception {
		Query q = em.createQuery(
				"from FileQuestion f join f.materiPengajar mp where mp.materi.id = :materi and mp.trainer.trainerId = :pengajar");
		q.setParameter("mater", materi).setParameter("pengajar", pengajar);
		return (FileQuestion) q.getSingleResult();
	}

	@Override
	public FileQuestion update(FileQuestion file) throws Exception {
		em.merge(file);
		return file;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findById(String hsId) throws Exception {
		Query q = em.createQuery("select fs.fileName, fs.file, fs.fileType from FileQuestion fs where fs.id = :id")
				.setParameter("id", hsId);
		return bMapperHibernate(q.getResultList(), "judul", "file", "type");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findDateTask(LocalDate tanggal, String materiPengajar, LocalTime waktu)
			throws Exception {
		Query q = em.createNativeQuery(
				"SELECT headerquestionid, title, day, startdate, enddate, type, starttime FROM tb_file_question_headers WHERE ( ? BETWEEN startdate AND enddate ) and materipengajar_id = ? and starttime <= ? and type = 'TUGAS'")
				.setParameter(1, tanggal).setParameter(2, materiPengajar).setParameter(3, waktu);
		List<Map<String, Object>> results = q.getResultList();
		return !results.isEmpty()
				? bMapperHibernate(q.getResultList(), "header_soal_id", "title", "day", "start_date", "end_date",
						"type", "time")
				: null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findDateExam(LocalTime waktu, String materiPengajar, LocalDate startDate)
			throws Exception {
		Query q = em.createNativeQuery(
				"SELECT headerquestionid, title, day, starttime, endtime, type, startdate FROM tb_file_question_headers WHERE ( ? BETWEEN starttime AND endtime ) and materipengajar_id = ? and startdate <= ? and type = 'UJIAN'")
				.setParameter(1, waktu).setParameter(2, materiPengajar).setParameter(3, startDate);
		List<Map<String, Object>> results = q.getResultList();
		return !results.isEmpty()
				? bMapperHibernate(q.getResultList(), "header_soal_id", "title", "day", "start_time", "end_time",
						"type", "start_date")
				: null;
	}

	@Override
	public FileQuestion findSoal(String idMateriPengajar) throws Exception {
		Query q = em.createQuery(
				"select f.fileName, f.day, f.type, f.startDate, f.endDate from FileQuestion f where f.materiPengajar.id = :id")
				.setParameter("id", idMateriPengajar);
		return (FileQuestion) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findJumSoal(String hsId) throws Exception {
		Query q = em.createNativeQuery("select tfs.title, thfs.starttime, tfs.id  from tb_file_question_headers thfs "
				+ "join tb_file_questions tfs on thfs.headerquestionid = tfs.file_question_header_id where thfs.headerquestionid = ?");
		q.setParameter(1, hsId);
		return bMapperHibernate(q.getResultList(), "title", "time", "soal_id");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String findHeaderIdTugas(String materiPengajar, String day) throws Exception {
		Query q = em.createNativeQuery(
				"select thfs.headerquestionid from tb_file_question_headers thfs where thfs.materipengajar_id = ? and thfs.type = 'TUGAS' and thfs.day = ?")
				.setParameter(1, materiPengajar).setParameter(2, day);
		List results = q.getResultList();
		return !results.isEmpty() ? (String) results.get(0) : null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String findHeaderIdUjian(String materiPengajar, String days, Date startdate, Date enddate) throws Exception {
		Query q = em.createNativeQuery(
				"select headerquestionid from tb_file_question_headers where materipengajar_id = ? and type = 'UJIAN'"
						+ "and day = ? and startdate = ? and enddate = ? ")
				.setParameter(1, materiPengajar).setParameter(2, days).setParameter(3, startdate)
				.setParameter(4, enddate);
		List results = q.getResultList();
		return !results.isEmpty() ? (String) results.get(0) : null;
	}

	@Override
	public void updateSoal(FileQuestion soal) throws Exception {
		em.merge(soal);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public FileQuestion findFileById(String id) throws Exception {
		Query q = em.createQuery("from FileQuestion where id = :id").setParameter("id", id);
		List results = q.getResultList();
		return !results.isEmpty() ? (FileQuestion) results.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteFileSoalByHeaderId(String id) throws Exception {
		Query q = em.createQuery("from FileQuestion f where f.fileQuestionHeader.headerQuestionId =: idParam ")
				.setParameter("idParam", id);
		List<FileQuestion> file = q.getResultList();
		file.forEach(x -> {
			em.remove(x);
		});

	}

	@Override
	public void deleteFileSoalById(FileQuestion filesoal) throws Exception {
		em.remove(filesoal);
	}

}
