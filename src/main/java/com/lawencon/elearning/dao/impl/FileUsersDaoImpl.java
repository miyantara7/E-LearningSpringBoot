package com.lawencon.elearning.dao.impl;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.FileUsersDao;
import com.lawencon.elearning.model.FileUsers;

@Repository
public class FileUsersDaoImpl extends BaseHibernate implements FileUsersDao {

	@Override
	public FileUsers insert(FileUsers file) throws Exception {
		em.persist(file);
		return file;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public FileUsers findBySoalHeader(String user, String soalHeader) throws Exception {
		Query q = em.createQuery(
				"from FileUsers f where f.fileQuestionHeader.headerQuestionId = :header and f.user.id = :user");
		q.setParameter("header", soalHeader).setParameter("user", user);
		List results = q.getResultList();
		return !results.isEmpty() ? (FileUsers) results.get(0) : null;
	}

	@Override
	public FileUsers update(FileUsers file) throws Exception {
		em.merge(file);
		return file;
	}

	@Override
	public FileUsers findById(String id) throws Exception {
		Query q = em.createQuery("from FileUsers where id = :id").setParameter("id", id);
		return (FileUsers) q.getSingleResult();
	}

	@Override
	public Date findDateTask(String id) throws Exception {
		Query q = em.createNativeQuery("select enddate from tb_file_question_headers tfu where headerquestionid = ?")
				.setParameter(1, id);
		return (Date) q.getSingleResult();
	}

	@Override
	public LocalTime findTimeExam(String id) throws Exception {
		Query q = em.createQuery("select endTime from FileQuestionHeader where headerQuestionId = :id")
				.setParameter("id", id);
		return (LocalTime) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteFileByHeaderId(String id) throws Exception {
		Query q = em.createQuery("from FileUsers f where f.fileQuestionHeader.headerQuestionId =: idParam ")
				.setParameter("idParam", id);
		List<FileUsers> file = q.getResultList();
		file.forEach(x -> {
			em.remove(x);
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> viewFileuser(String kelasId, String userId) throws Exception {
		Query q = em
				.createNativeQuery("select tfu.id, tfs.title,tfs.type, tfs.day, tfs.startdate from tb_file_users tfu "
						+ "join tb_file_question_headers tfs on tfu.file_question_header_id = tfs.headerquestionid "
						+ "join tb_materi_pengajar tm on tm.id = tfs.materipengajar_id where "
						+ "tfs.materipengajar_id = ? and tfu.user_id = ? order by tfu.date desc")
				.setParameter(1, kelasId).setParameter(2, userId);
		return bMapperHibernate(q.getResultList(), "id_file", "title", "type", "day", "question_date");
	}
}
