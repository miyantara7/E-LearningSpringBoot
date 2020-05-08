package com.lawencon.elearning.dao;

import java.time.LocalDate;
import com.lawencon.elearning.model.Attendance;

public interface AttendanceDao {

	Attendance insert(Attendance absen) throws Exception;

	Attendance update(Attendance absen) throws Exception;

	void delete(String id) throws Exception;

	String findByUserMateriPengajar(String user, String materiPengajarId, LocalDate date) throws Exception;

}
