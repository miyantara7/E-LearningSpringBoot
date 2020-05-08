package com.lawencon.elearning.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.lawencon.elearning.model.FileQuestionHeader;
import com.lawencon.elearning.service.FileQuestionHeaderService;
import com.lawencon.elearning.service.FileQuestionService;

@RestController
@CrossOrigin("*")
public class FileQuestionController extends BaseController<FileQuestionHeader> {

	@Autowired
	private FileQuestionService fileSoalService;

	@Autowired
	private FileQuestionHeaderService fileHeaderSoalService;

	@PostMapping("file-soal/upload")
	public ResponseEntity<?> uploadTask(@RequestParam("file") MultipartFile file,
			@RequestParam("kId") String idMateriPengajar, @RequestParam("jenis") String jenis,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
			@RequestParam("judul") String judul) {
		try {
			fileSoalService.insert(file, idMateriPengajar, jenis, startDate, endDate, startTime, endTime, judul);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("file-soal/download")
	public ResponseEntity<?> downloadTask(@RequestParam("id") String soalid) {
		List<Map<String, Object>> fileSoal = new ArrayList<>();
		try {
			fileSoal = fileSoalService.findById(soalid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileSoal.get(0).get("type").toString()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + fileSoal.get(0).get("judul") + "\"")
				.body(fileSoal.get(0).get("file"));

	}

	@GetMapping("/tugas/find")
	public ResponseEntity<?> findTugas(@RequestParam("kId") String kId) {
		List<Map<String, Object>> file = new ArrayList<>();
		try {
			file = fileSoalService.findDateTask(kId);
			return new ResponseEntity<>(file, HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	@GetMapping("ujian/find")
	public ResponseEntity<?> findUjian(@RequestParam("kId") String kId) {
		List<Map<String, Object>> file = new ArrayList<>();
		try {
			file = fileSoalService.findDateExam(kId);
			return new ResponseEntity<>(file, HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	@GetMapping("soal/jum")
	public ResponseEntity<?> findJumSoal(@RequestParam("hId") String sId) {
		List<Map<String, Object>> file = new ArrayList<>();
		try {
			file = fileSoalService.findJumSoal(sId);
			return new ResponseEntity<>(file, HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	@PostMapping("update/soal-detail")
	public ResponseEntity<?> updateSoal(@RequestParam("id") String id, @RequestParam("file") MultipartFile file,
			@RequestParam("judul") String judul) {
		String pesan = null;
		try {
			pesan = fileSoalService.updateSoal(id, file, judul);
			return new ResponseEntity<>(pesan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(pesan, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("update/ujian-detail")
	public ResponseEntity<?> updateSoal(@RequestBody String header) {
		String pesan = null;
		FileQuestionHeader file = new FileQuestionHeader();
		try {
			file = readValue(header, FileQuestionHeader.class);
			pesan = fileSoalService.updateSoalUjian(file);
			return new ResponseEntity<>(pesan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(pesan, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("update/soal-header")
	public ResponseEntity<?> updateHeader(@RequestParam("hId") String hId, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime) {
		String pesan = null;
		try {
			pesan = fileSoalService.updateHeader(hId, startDate, endDate, startTime, endTime);
			return new ResponseEntity<>(pesan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(pesan, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("view/soal-tugas")
	public ResponseEntity<?> viewHeaderTugas(@RequestParam("kId") String kId) {
		List<Map<String, Object>> file = new ArrayList<>();
		try {
			file = fileHeaderSoalService.findSoalHeader(kId);
			return new ResponseEntity<>(file, HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	@GetMapping("view/soal-ujian")
	public ResponseEntity<?> viewHeaderUjian(@RequestParam("kId") String kId) {
		List<Map<String, Object>> file = new ArrayList<>();
		try {
			file = fileHeaderSoalService.findSoalHeaderUjian(kId);
			return new ResponseEntity<>(file, HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	@PostMapping("file-soal/delete-header")
	public ResponseEntity<?> deleteHeader(@RequestParam("id") String id) {
		String pesan = null;
		try {
			fileSoalService.deleteFileHeader(id);
			return new ResponseEntity<>(pesan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(pesan, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("file-soal/delete")
	public ResponseEntity<?> deleteFileSoal(@RequestParam("id") String id) {
		try {
			fileSoalService.deleteFileSoalById(id);
			return new ResponseEntity<>("Berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Gagal", HttpStatus.BAD_REQUEST);
	}

}
