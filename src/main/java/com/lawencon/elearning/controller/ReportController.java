package com.lawencon.elearning.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.elearning.service.ReportService;

@RestController
@CrossOrigin("*")
public class ReportController {

	@Autowired
	private ReportService reportservice;

	@GetMapping("/report/nilaiakhirdetail")
	public ResponseEntity<String> getGenerate(@RequestParam("idUser") String idUser, @RequestParam("mpId") String mpId,
			@RequestParam("classId") String classId, HttpServletResponse response) throws Exception {
		try {
			return new ResponseEntity<String>(reportservice.downloadNilaiAkhirDetail(response, idUser, mpId, classId),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/nilaiakhirdetaillist")
	public ResponseEntity<?> findKelasByUserList(@RequestParam("idUser") String idUser,
			@RequestParam("mpId") String mpId, @RequestParam("classId") String classId) throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportservice.nilaiAkhirDetail(idUser, mpId, classId);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/nilaiakhirdetaillistuser")
	public ResponseEntity<?> nilaiAkhrirDetailUser(@RequestParam("idUser") String idUser) throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportservice.nilaiAkhirDetailByUser(idUser);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/nilaiakhir")
	public ResponseEntity<String> getNilaiAkhir(@RequestParam("idUser") String idUser, HttpServletResponse response)
			throws Exception {
		try {
			return new ResponseEntity<String>(reportservice.downloadNilaiAkhir(response, idUser), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/rinciannilai/download")
	public ResponseEntity<String> downloadRincianNilai(@RequestParam("materiId") String materiId,
			@RequestParam("classId") String classId, HttpServletResponse response,
			@RequestParam("period") String period) throws Exception {
		try {
			return new ResponseEntity<String>(reportservice.downloadRincianNilai(materiId, classId, response, period),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/nilaipesertabympclass/download")
	public ResponseEntity<String> nilaiPesertaByMPClass(@RequestParam("materiId") String materiId,
			@RequestParam("classId") String classId, HttpServletResponse response) throws Exception {
		try {
			return new ResponseEntity<String>(reportservice.nilaiPesertaByMPClass(materiId, classId, response),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/all-jadwal/download")
	public ResponseEntity<?> downloadJadwal(HttpServletResponse response) throws Exception {
		try {
			return new ResponseEntity<>(reportservice.downloadFindAllJadwal(response), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/jadwals/download")
	public ResponseEntity<?> downloadJadwals(@RequestParam("tgl1") String tgl1, @RequestParam("tgl2") String tgl2,
			HttpServletResponse response) throws Exception {
		try {
			return new ResponseEntity<>(reportservice.downloadJadwalPeriode(tgl1, tgl2, response), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/all-mapel/download")
	public ResponseEntity<?> downloadMapel(HttpServletResponse response) throws Exception {
		try {
			return new ResponseEntity<>(reportservice.downloadFindAllMapel(response), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/kelas/download")
	public ResponseEntity<?> downloadKelas(@RequestParam("cId") String classId, @RequestParam("mId") String materiId,
			HttpServletResponse response) throws Exception {
		try {
			return new ResponseEntity<>(reportservice.downloadPesertaClassByMateriClass(materiId, classId, response),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/mapels/download")
	public ResponseEntity<?> downloadMapels(@RequestParam("cId") String classId,
			@RequestParam("mId") String materiPengajarId, HttpServletResponse response) throws Exception {
		try {
			return new ResponseEntity<>(reportservice.downloadFindByClassMateri(classId, materiPengajarId, response),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/kehadiran/download")
	public ResponseEntity<?> kehadiranDownload(@RequestParam("materiId") String materiId,
			@RequestParam("classId") String classId, HttpServletResponse response) throws Exception {
		try {
			return new ResponseEntity<>(reportservice.downloadKehadiran(materiId, classId, response),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/all-jadwal")
	public ResponseEntity<?> getJadwal() throws Exception {
		List<?> listJadwal = new ArrayList<>();
		try {
			listJadwal = reportservice.findAllJadwal();
			return new ResponseEntity<>(listJadwal, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listJadwal, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/all-kelas")
	public ResponseEntity<?> kelas() throws Exception {
		List<?> listKelas = new ArrayList<>();
		try {
			listKelas = reportservice.findAllKelas();
			return new ResponseEntity<>(listKelas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/kelass")
	public ResponseEntity<?> getPeserta(@RequestParam("cId") String classId, @RequestParam("mId") String materiId)
			throws Exception {
		List<?> listPeserta = new ArrayList<>();
		try {
			listPeserta = reportservice.findPesertaByMateriClass(materiId, classId);
			return new ResponseEntity<>(listPeserta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(listPeserta, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/jadwals")
	public ResponseEntity<?> getJadwals(@RequestParam("tgl1") String tgl1, @RequestParam("tgl2") String tgl2)
			throws Exception {
		List<Map<String, Object>> listJadwal = new ArrayList<>();
		try {
			listJadwal = reportservice.getJadwalPeriode(tgl1, tgl2);
			return new ResponseEntity<>(listJadwal, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(listJadwal, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/all-mapel")
	public ResponseEntity<?> getMapel() throws Exception {
		List<?> listJadwal = new ArrayList<>();
		try {
			listJadwal = reportservice.findAllMapel();
			return new ResponseEntity<>(listJadwal, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(listJadwal, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/mapels")
	public ResponseEntity<?> getMapels(@RequestParam("cId") String classId,
			@RequestParam("mId") String materiPengajarId) throws Exception {
		List<?> listJadwal = new ArrayList<>();
		try {
			listJadwal = reportservice.findByClassMateri(classId, materiPengajarId);
			return new ResponseEntity<>(listJadwal, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(listJadwal, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/findmateribyuser")
	public ResponseEntity<?> findMateriByUser(@RequestParam("userId") String userId) throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportservice.findMateriByUser(userId);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/findkelasbyuser")
	public ResponseEntity<?> findKelasByUser(@RequestParam("userId") String userId) throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportservice.findKelasByUser(userId);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/rinciannilai")
	public ResponseEntity<?> rincianNilai(@RequestParam("materiId") String materiId,
			@RequestParam("classId") String classId, @RequestParam("period") String period) throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportservice.rincianNilai(materiId, classId, period);
			return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/report/kehadiran")
	public ResponseEntity<?> kehadiran(@RequestParam("materiId") String materiId,
			@RequestParam("classId") String classId) throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportservice.findKehadiran(materiId, classId);
			return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
	}

}
