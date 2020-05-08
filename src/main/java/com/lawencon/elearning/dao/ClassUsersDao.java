package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Map;

import com.lawencon.elearning.model.Class;
import com.lawencon.elearning.model.ClassUsers;

public interface ClassUsersDao {
	String findClass(String uId, String idMateriPengajar) throws Exception;

	ClassUsers insertKelas(ClassUsers kelas) throws Exception;

	ClassUsers findByMateriUser(String user, String materiPengajar) throws Exception;

	String findMateriPengajarJam(String materi, String pengajar, String jam, String user) throws Exception;

	List<Map<String, Object>> listKelas(String mId, String pId) throws Exception;

	String cekClass(String uId, String jam) throws Exception;

	List<Map<String, Object>> viewUserClass(String id) throws Exception;

	List<Class> viewAllClass() throws Exception;
}
