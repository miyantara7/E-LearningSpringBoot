package com.lawencon.elearning.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawencon.elearning.dao.AttendanceDao;
import com.lawencon.elearning.model.Attendance;
import com.lawencon.elearning.model.MateriPengajar;
import com.lawencon.elearning.model.Users;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDao absenService;

	@Override
	public Attendance insert(String user, String materiPengajarId) throws Exception {
		String absen = null;
		Attendance ab = new Attendance();
		MateriPengajar mp = new MateriPengajar();
		Users u = new Users();
		LocalDate date = LocalDate.now();
		try {
			absen = absenService.findByUserMateriPengajar(user, materiPengajarId, date);
			if (absen == null) {
				ab.setDate(date);
				u.setId(user);
				mp.setId(materiPengajarId);
				ab.setMateriPengajar(mp);
				ab.setUser(u);
				return absenService.insert(ab);
			} else {
				throw new Exception("ANDA SUDAH ABSEN !");
			}
		} catch (Exception e) {
			throw new Exception();
		}

	}

	@Override
	public Boolean checkAbsen(String user, String mpId) throws Exception {
		LocalDate date = LocalDate.now();
		String absen = null;
		DayOfWeek hari = LocalDate.parse(date.toString()).getDayOfWeek();
		String days = String.valueOf(hari);
		System.out.println(hari);
		try {
			absen = absenService.findByUserMateriPengajar(user, mpId, date);
			if (absen != null || days.equals("SATURDAY") || days.equals("SUNDAY")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new Exception();
		}

	}

}
