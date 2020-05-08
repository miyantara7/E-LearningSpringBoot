package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.service.TaskScoreService;

@RestController
@CrossOrigin("*")
public class ScoresController {

	@Autowired
	private TaskScoreService nilaiTugasService;

	@PostMapping("/insert/nilai")
	public ResponseEntity<?> insertPengajar(@RequestParam("nilai") int nilai, @RequestParam("kelas") String kelas,
			@RequestParam("userId") String userId, @RequestParam("jenis") String jenis,
			@RequestParam("keaktifan") int keaktifan, @RequestParam("tanggal") String tanggal,
			@RequestParam("title") String title, @RequestParam("period") String period) {
		try {
			nilaiTugasService.insert(nilai, userId, kelas, keaktifan, jenis, tanggal, title, period);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to insert", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@PostMapping("/update/nilai")
	public ResponseEntity<?> updatePengajar(@RequestParam("id") String id, @RequestParam("nilai") int nilai,
			@RequestParam("kelas") String kelas, @RequestParam("userId") String userId,
			@RequestParam("jenis") String jenis, @RequestParam("keaktifan") int keaktifan,
			@RequestParam("period") String period) {
		try {
			nilaiTugasService.update(id, nilai, userId, kelas, keaktifan, jenis, period);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@PostMapping("/delete/nilai")
	public ResponseEntity<?> deletePengajar(@RequestParam("id") String id, @RequestParam("kelas") String kelas,
			@RequestParam("userId") String userId, @RequestParam("jenis") String jenis,
			@RequestParam("period") String period) {
		try {
			nilaiTugasService.delete(id, userId, kelas, jenis, period);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
