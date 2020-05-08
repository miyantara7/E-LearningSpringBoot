package com.lawencon.elearning.dao.impl;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.FileQuestionHeaderDao;
import com.lawencon.elearning.model.FileQuestionHeader;

@Repository
public class FileQuestionHeaderDaoImpl extends BaseHibernate implements FileQuestionHeaderDao {

	@Override
	public FileQuestionHeader insertHeaderSoal(FileQuestionHeader filesoalheader) throws Exception {
		em.persist(filesoalheader);
		return filesoalheader;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public FileQuestionHeader findFileSoalHeaderById(String id) throws Exception {
		Query q = em.createQuery("from FileQuestionHeader where headerQuestionId = :id").setParameter("id", id);
		List results = q.getResultList();
		return !results.isEmpty() ? (FileQuestionHeader) results.get(0) : null;
	}

	@Override
	public void updateHeader(FileQuestionHeader header) throws Exception {
		em.merge(header);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> viewSoalHeader(String kId) throws Exception {
		Query q = em.createQuery(
				"select  headerQuestionId, title ,type, startDate, endDate, startTime, endTime from FileQuestionHeader fh "
						+ "where fh.materiPengajar.id =: idParam and type = 'TUGAS'")
				.setParameter("idParam", kId);
		List<Map<String, Object>> results = q.getResultList();
		return !results.isEmpty()
				? bMapperHibernate(q.getResultList(), "header_id", "title", "type", "startDate", "endDate", "startTime",
						"endTime")
				: null;
	}

	@Override
	public void deleteFileHeader(FileQuestionHeader filesoalheader) throws Exception {
		em.remove(filesoalheader);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> viewSoalHeaderUjian(String kId) throws Exception {
		Query q = em.createQuery(
				"select  headerQuestionId, title ,type, startDate, endDate, startTime, endTime from FileQuestionHeader fh "
						+ "where fh.materiPengajar.id =: idParam and type = 'UJIAN'")
				.setParameter("idParam", kId);
		List<Map<String, Object>> results = q.getResultList();
		return !results.isEmpty()
				? bMapperHibernate(q.getResultList(), "header_id", "title", "type", "startDate", "endDate", "startTime",
						"endTime")
				: null;
	}

}
