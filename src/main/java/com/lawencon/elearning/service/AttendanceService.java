package com.lawencon.elearning.service;

import com.lawencon.elearning.model.Attendance;

public interface AttendanceService {

	Attendance insert(String user, String materiPengajarId) throws Exception;

	Boolean checkAbsen(String user, String mpId) throws Exception;
}
