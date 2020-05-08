package com.lawencon.elearning.service;

import java.util.List;

import com.lawencon.elearning.model.ForumDetail;
import com.lawencon.elearning.model.ForumQuiz;
import com.lawencon.elearning.model.ForumHeader;
import com.lawencon.elearning.model.ForumQuizHeader;

public interface ForumService {

	void insertChat(String forumId, String sender, String isiPesan) throws Exception;

	List<ForumDetail> findChatByHeaderId(String id) throws Exception;

	ForumHeader checkHeader(String id) throws Exception;

	void deleteHeader(ForumHeader header) throws Exception;

	void deleteChat(ForumDetail detail) throws Exception;

	List<ForumDetail> findAllChat(String idHeaderForum) throws Exception;

	void insertChatSoal(String forumId, String sender, String isiPesan) throws Exception;

	List<ForumQuiz> findChatByHeaderSoalId(String id) throws Exception;

	ForumQuizHeader checkHeaderSoal(String id) throws Exception;

	void deleteHeaderSoal(ForumQuizHeader header) throws Exception;

	void deleteChatSoal(ForumQuiz detail) throws Exception;

	List<ForumQuiz> findAllChatSoal(String idHeaderForum) throws Exception;

}
