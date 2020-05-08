package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.Class;
import com.lawencon.elearning.model.ClassUsers;

public interface ClassUsersService {
	boolean findClass(String uId, String idMateriPengajar) throws Exception;

	ClassUsers insertKelas(String idMateriPengajar, String uId) throws Exception;

	List<Map<String, Object>> listKelas(String mId, String pId) throws Exception;

	boolean cekClass(String uId, String jam) throws Exception;

	List<Map<String, Object>> viewUserClass(String id) throws Exception;

	List<Class> viewAllClass() throws Exception;
}
