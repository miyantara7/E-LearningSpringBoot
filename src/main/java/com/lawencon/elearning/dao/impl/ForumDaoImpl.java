package com.lawencon.elearning.dao.impl;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.lawencon.elearning.dao.ForumDao;
import com.lawencon.elearning.model.ForumDetail;
import com.lawencon.elearning.model.ForumQuiz;
import com.lawencon.elearning.model.ForumHeader;
import com.lawencon.elearning.model.ForumQuizHeader;

@Repository
public class ForumDaoImpl extends BaseHibernate implements ForumDao {

	@Override
	public void insertChat(ForumDetail detail) throws Exception {
		em.persist(detail);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ForumHeader checkHeader(String id) throws Exception {
		Query q = em.createQuery("from ForumHeader where materiHeader.id = :id").setParameter("id", id);
		List results = q.getResultList();
		return !results.isEmpty() ? (ForumHeader) results.get(0) : null;
	}

	@Override
	public void insertHeader(ForumHeader header) throws Exception {
		em.persist(header);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumDetail> findChatByHeaderId(String id) throws Exception {
		Query q = em.createNativeQuery("select sender, senddate , chatcountent from tb_forum_details "
				+ "where forum_header_id = ? order by senddate asc").setParameter(1, id);
		return bMapperHibernate(q.getResultList(), "sender", "send_Date", "chat_content");
	}

	@Override
	public void deleteHeader(ForumHeader header) throws Exception {
		em.remove(header);

	}

	@Override
	public void deleteChat(ForumDetail detail) throws Exception {
		em.remove(detail);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ForumDetail> findAllChat(String idHeaderForum) throws Exception {
		Query q = em.createQuery("from ForumDetail d where d.forumHeader.id =: idParam").setParameter("idParam",
				idHeaderForum);
		List result = q.getResultList();
		return !result.isEmpty() ? q.getResultList() : null;
	}

	@Override
	public void insertChatSoal(ForumQuiz detail) throws Exception {
		em.persist(detail);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ForumQuiz> findChatByHeaderSoalId(String id) throws Exception {
		Query q = em.createNativeQuery("select sender, senddate , chatcountent from tb_forum_quiz "
				+ "where forum_quiz_header_id = ? order by senddate asc").setParameter(1, id);
		return bMapperHibernate(q.getResultList(), "sender", "send_Date", "chat_content");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ForumQuizHeader checkHeaderSoal(String id) throws Exception {
		Query q = em.createQuery("from ForumQuizHeader h where h.fileQuestionHeader.headerQuestionId =: idParam")
				.setParameter("idParam", id);
		List results = q.getResultList();
		return !results.isEmpty() ? (ForumQuizHeader) results.get(0) : null;
	}

	@Override
	public void deleteHeaderSoal(ForumQuizHeader header) throws Exception {
		em.remove(header);

	}

	@Override
	public void deleteChatSoal(ForumQuiz detail) throws Exception {
		em.remove(detail);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ForumQuiz> findAllChatSoal(String idHeaderForum) throws Exception {
		Query q = em.createQuery("from ForumQuiz d where d.forumQuizHeader.id =: idParam").setParameter("idParam",
				idHeaderForum);
		List result = q.getResultList();
		return !result.isEmpty() ? q.getResultList() : null;
	}

	@Override
	public void insertHeaderSoal(ForumQuizHeader header) throws Exception {
		em.persist(header);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String checkHeaderSoalByid(String id) throws Exception {
		Query q = em.createNativeQuery("select id from tb_forum_quiz_headers where file_question_header_id = ?")
				.setParameter(1, id);
		List results = q.getResultList();
		return !results.isEmpty() ? (String) results.get(0) : null;
	}

}
