package com.lawencon.elearning.service;

import java.time.LocalDate;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lawencon.elearning.dao.FinalScoreDao;
import com.lawencon.elearning.model.MateriPengajar;
import com.lawencon.elearning.model.FinalScore;
import com.lawencon.elearning.model.Users;

@Service
@Transactional
public class FinalScoreServiceImpl implements FinalScoreService {

	@Autowired
	private FinalScoreDao nilaiTotalDao;

	@Override
	public String getNilaiTotal(String idUser, String mpId, String jenis, String period) throws Exception {
		Integer sumTugas = ((Long) nilaiTotalDao.getNilaiTugasByUser(idUser, mpId)).intValue();
		Integer ujian = nilaiTotalDao.getNilaiUjianByUser(idUser, mpId);
		Integer sumSoal = 0;
		Integer sumUjian = 0;
		sumSoal = nilaiTotalDao.getCountSoalTugas(mpId);
		sumUjian = nilaiTotalDao.getCountSoalUjian(mpId);
		if (sumSoal == 0) {
			sumSoal = 1;
		} else if (sumUjian == 0) {
			sumUjian = 1;
		}
		Integer rataTugas = sumTugas / sumSoal;
		Integer rataUjian = ujian / sumUjian;
		Double totalNilai = 0.6 * rataTugas + 0.4 * rataUjian;
		FinalScore nilaiAkhir = null;
		FinalScore nilaiAkhir2 = new FinalScore();
		MateriPengajar mp = new MateriPengajar();
		Users user = new Users();
		if (totalNilai > 100) {
			totalNilai = Double.valueOf(100);
		}
		try {
			nilaiAkhir = nilaiTotalDao.checkNilai(idUser, mpId);
			if (nilaiAkhir != null) {
				nilaiAkhir.setFinalScore(totalNilai);
				nilaiTotalDao.update(nilaiAkhir);
				return "Berhasil Update Nilai";
			} else {
				mp.setId(mpId);
				user.setId(idUser);
				nilaiAkhir2.setPeriodDate(LocalDate.parse(period));
				nilaiAkhir2.setMateriPengajar(mp);
				nilaiAkhir2.setUser(user);
				nilaiAkhir2.setFinalScore(totalNilai);
				nilaiTotalDao.insert(nilaiAkhir2);

				return "Berhasil Input Nilai";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
