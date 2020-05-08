package com.lawencon.elearning.service;

import java.time.LocalDate;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawencon.elearning.dao.TaskScoreDao;
import com.lawencon.elearning.model.JenisFile;
import com.lawencon.elearning.model.MateriPengajar;
import com.lawencon.elearning.model.TaskScore;
import com.lawencon.elearning.model.Users;;

@Service
@Transactional
public class TaskScoreServiceImpl implements TaskScoreService {

	@Autowired
	private TaskScoreDao nilaiTugasService;

	@Autowired
	private ExamScoreService nilaiUjianService;

	@Autowired
	private FinalScoreService nilaiAkhirService;

	@Override
	public String insert(int nilai, String userId, String mpId, int keaktifan, String jenis, String tanggal,
			String title, String period) throws Exception {
		TaskScore nilaiTugas = new TaskScore();
		MateriPengajar mp = new MateriPengajar();
		Users user = new Users();
		if (jenis.equalsIgnoreCase(JenisFile.UJIAN.name())) {
			return nilaiUjianService.insert(nilai, userId, mpId, tanggal, title, period);

		} else {
			mp.setId(mpId);
			user.setId(userId);
			nilaiTugas.setUser(user);
			nilaiTugas.setTitle(title);
			nilaiTugas.setMateriPengajar(mp);
			nilaiTugas.setDate(LocalDate.parse(tanggal));
			int nilaiFix = nilai + keaktifan;
			if (nilaiFix > 100) {
				nilaiFix = 100;
			}
			nilaiTugas.setScore(nilaiFix);
			nilaiTugasService.insert(nilaiTugas);
			nilaiAkhirService.getNilaiTotal(userId, mpId, jenis, period);
			return "Berhasil input nilai";
		}

	}

	@Override
	public void update(String id, int nilai, String userId, String mpId, int keaktifan, String jenis, String period)
			throws Exception {
		TaskScore nilaiTugas = null;
		if (jenis.equalsIgnoreCase(JenisFile.UJIAN.name())) {
			nilaiUjianService.update(id, nilai, userId, mpId, jenis, period);
		} else {
			try {
				nilaiTugas = nilaiTugasService.findById(id);
				if (nilaiTugas != null) {
					int nilaiFix = nilai + keaktifan;
					if (nilaiFix > 100) {
						nilaiFix = 100;
					}
					nilaiTugas.setScore(nilaiFix);
					nilaiTugasService.update(nilaiTugas);
					nilaiAkhirService.getNilaiTotal(userId, mpId, jenis, period);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void delete(String id, String userId, String mpId, String jenis, String period) throws Exception {
		TaskScore nilaiTugas = null;
		if (jenis.equalsIgnoreCase(JenisFile.UJIAN.name())) {
			nilaiUjianService.delete(id, userId, mpId, jenis, period);
		} else {
			try {
				nilaiTugas = nilaiTugasService.findById(id);
				if (nilaiTugas != null) {
					nilaiTugasService.delete(nilaiTugas);
					nilaiAkhirService.getNilaiTotal(userId, mpId, JenisFile.UJIAN.name(), period);
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				throw new Exception();
			}
		}

	}

}
