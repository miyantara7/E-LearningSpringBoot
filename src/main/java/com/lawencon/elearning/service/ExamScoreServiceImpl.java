package com.lawencon.elearning.service;

import java.time.LocalDate;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawencon.elearning.dao.ExamScoreDao;
import com.lawencon.elearning.model.JenisFile;
import com.lawencon.elearning.model.MateriPengajar;
import com.lawencon.elearning.model.ExamScore;
import com.lawencon.elearning.model.Users;

@Service
@Transactional
public class ExamScoreServiceImpl implements ExamScoreService {

	@Autowired
	private ExamScoreDao nilaiUjianService;

	@Autowired
	private FinalScoreService nilaiAkhirService;

	@Override
	public String insert(int nilai, String user, String mpId, String tanggal, String title, String period)
			throws Exception {
		ExamScore nilaiUjian = new ExamScore();
		MateriPengajar mp = new MateriPengajar();
		Users u = new Users();
		if (nilai > 100) {
			nilai = 100;
		}
		mp.setId(mpId);
		u.setId(user);
		nilaiUjian.setMateriPengajar(mp);
		nilaiUjian.setUser(u);
		nilaiUjian.setTitle(title);
		nilaiUjian.setDate(LocalDate.parse(tanggal));
		nilaiUjian.setScore(nilai);
		nilaiUjianService.insert(nilaiUjian);
		nilaiAkhirService.getNilaiTotal(user, mpId, JenisFile.UJIAN.name(), period);
		return "Berhasil Input Nilai";
	}

	@Override
	public void update(String id, int nilai, String user, String mpId, String jenis, String period) throws Exception {
		ExamScore nilaiUjian = null;
		try {
			nilaiUjian = nilaiUjianService.findById(id);
			if (nilaiUjian != null) {
				if (nilai > 100) {
					nilai = 100;
				}
				nilaiUjian.setScore(nilai);
				nilaiUjianService.update(nilaiUjian);
				nilaiAkhirService.getNilaiTotal(user, mpId, JenisFile.UJIAN.name(), period);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String id, String user, String mpId, String jenis, String period) throws Exception {
		ExamScore nilaiUjian = null;
		try {
			nilaiUjian = nilaiUjianService.findById(id);
			if (nilaiUjian != null) {
				nilaiUjianService.delete(nilaiUjian);
				nilaiAkhirService.getNilaiTotal(user, mpId, JenisFile.UJIAN.name(), period);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception();
		}

	}

}
