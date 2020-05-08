package com.lawencon.elearning.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawencon.elearning.dao.ForumDao;
import com.lawencon.elearning.model.ForumDetail;
import com.lawencon.elearning.model.ForumQuiz;
import com.lawencon.elearning.model.FileQuestionHeader;
import com.lawencon.elearning.model.ForumHeader;
import com.lawencon.elearning.model.ForumQuizHeader;
import com.lawencon.elearning.model.MateriHeader;

@Service
@Transactional
public class ForumServiceImpl implements ForumService {

	@Autowired
	private ForumDao forumDao;

	@Autowired
	private UserService userservice;

	@Autowired
	private TrainerService pengajarservice;

	@Override
	public void insertChat(String forumId, String sender, String isiPesan) throws Exception {
		ForumHeader headerForum = null;
		ForumHeader headerForum2 = new ForumHeader();
		ForumDetail detail = new ForumDetail();
		MateriHeader topic = new MateriHeader();
		String username = null;
		String pengajarr = null;
		String[] format = new String[] { "yyyy-MM-dd" };
		Date now = DateUtils.parseDate(LocalDate.now().toString(), format);
		detail.setSendDate(now);
		detail.setChatCountent(isiPesan);
		try {
			username = userservice.getUserById(sender);
			if (username != null) {
				detail.setSender(username);
			} else {
				pengajarr = pengajarservice.findById(sender);
				if (pengajarr != null) {
					detail.setSender(pengajarr);
				} else {
					throw new Exception("User not found !");
				}
			}
			headerForum = forumDao.checkHeader(forumId);
			if (headerForum != null) {
				detail.setForumHeader(headerForum);
				forumDao.insertChat(detail);
			} else {
				topic.setHeaderId(forumId);
				headerForum2.setMateriHeader(topic);
				detail.setForumHeader(headerForum2);
				forumDao.insertHeader(headerForum2);
				forumDao.insertChat(detail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	@Override
	public List<ForumDetail> findChatByHeaderId(String id) throws Exception {
		ForumHeader header = null;
		try {
			header = forumDao.checkHeader(id);
			if (header == null)
				return null;
			else
				return forumDao.findChatByHeaderId(header.getId());
		} catch (Exception e) {
			throw new Exception();
		}

	}

	@Override
	public ForumHeader checkHeader(String id) throws Exception {
		return forumDao.checkHeader(id);
	}

	@Override
	public void deleteHeader(ForumHeader header) throws Exception {
		forumDao.deleteHeader(header);

	}

	@Override
	public void deleteChat(ForumDetail detail) throws Exception {
		forumDao.deleteChat(detail);

	}

	@Override
	public List<ForumDetail> findAllChat(String idHeaderForum) throws Exception {
		return forumDao.findAllChat(idHeaderForum);
	}

	@Override
	public void insertChatSoal(String forumId, String sender, String isiPesan) throws Exception {
		String headerForum = null;
		ForumQuizHeader headerForum2 = new ForumQuizHeader();
		ForumQuiz detail = new ForumQuiz();
		FileQuestionHeader topic = new FileQuestionHeader();
		String username = null;
		String pengajarr = null;
		String[] format = new String[] { "yyyy-MM-dd" };
		Date now = DateUtils.parseDate(LocalDate.now().toString(), format);
		detail.setSendDate(now);
		detail.setChatCountent(isiPesan);
		try {
			username = userservice.getUserById(sender);
			if (username != null) {
				detail.setSender(username);
			} else {
				pengajarr = pengajarservice.findById(sender);
				if (pengajarr != null) {
					detail.setSender(pengajarr);
				} else {
					throw new Exception();
				}
			}
			headerForum = forumDao.checkHeaderSoalByid(forumId);
			if (headerForum != null) {
				headerForum2.setId(headerForum);
				detail.setForumQuizHeader(headerForum2);
				forumDao.insertChatSoal(detail);
			} else {
				topic.setHeaderQuestionId(forumId);
				headerForum2.setFileQuestionHeader(topic);
				detail.setForumQuizHeader(headerForum2);
				forumDao.insertHeaderSoal(headerForum2);
				forumDao.insertChatSoal(detail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	@Override
	public List<ForumQuiz> findChatByHeaderSoalId(String id) throws Exception {
		String header = null;
		try {
			header = forumDao.checkHeaderSoalByid(id);
			if (header == null)
				return null;
			else
				return forumDao.findChatByHeaderSoalId(header);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public ForumQuizHeader checkHeaderSoal(String id) throws Exception {
		return forumDao.checkHeaderSoal(id);
	}

	@Override
	public void deleteHeaderSoal(ForumQuizHeader header) throws Exception {
		forumDao.deleteHeaderSoal(header);

	}

	@Override
	public void deleteChatSoal(ForumQuiz detail) throws Exception {
		forumDao.deleteChatSoal(detail);

	}

	@Override
	public List<ForumQuiz> findAllChatSoal(String idHeaderForum) throws Exception {
		return forumDao.findAllChatSoal(idHeaderForum);
	}

}
