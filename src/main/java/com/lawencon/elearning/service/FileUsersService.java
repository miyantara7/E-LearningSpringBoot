package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.elearning.model.FileUsers;

public interface FileUsersService {

	boolean insertUjian(MultipartFile file, String soalHeader, String user) throws Exception;

	FileUsers update(FileUsers file) throws Exception;

	FileUsers findById(String id) throws Exception;

	boolean insertTask(String idUser, String idHeader, MultipartFile file) throws Exception;

	void deleteFileUserByHeaderId(String id) throws Exception;

	List<Map<String, Object>> viewFileUser(String kelasId, String userId) throws Exception;

}
