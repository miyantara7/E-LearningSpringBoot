package com.lawencon.elearning.dao;

import java.util.List;

import com.lawencon.elearning.model.ForumDetail;
import com.lawencon.elearning.model.ForumQuiz;
import com.lawencon.elearning.model.ForumHeader;
import com.lawencon.elearning.model.ForumQuizHeader;

public interface ForumDao {

	void insertChat(ForumDetail detail) throws Exception;

	ForumHeader checkHeader(String id) throws Exception;

	void insertHeader(ForumHeader header) throws Exception;

	List<ForumDetail> findChatByHeaderId(String id) throws Exception;

	void deleteHeader(ForumHeader header) throws Exception;

	void deleteChat(ForumDetail detail) throws Exception;

	List<ForumDetail> findAllChat(String idHeaderForum) throws Exception;

	void insertChatSoal(ForumQuiz detail) throws Exception;

	void insertHeaderSoal(ForumQuizHeader header) throws Exception;

	List<ForumQuiz> findChatByHeaderSoalId(String id) throws Exception;

	ForumQuizHeader checkHeaderSoal(String id) throws Exception;

	void deleteHeaderSoal(ForumQuizHeader header) throws Exception;

	void deleteChatSoal(ForumQuiz detail) throws Exception;

	List<ForumQuiz> findAllChatSoal(String idHeaderForum) throws Exception;

	String checkHeaderSoalByid(String id) throws Exception;
}
