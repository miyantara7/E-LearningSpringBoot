package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.elearning.service.AttendanceService;

@RestController
@CrossOrigin("*")
public class AbsenController {

	@Autowired
	private AttendanceService absenService;

	@PostMapping("insert/kehadiran")
	public ResponseEntity<?> insert(@RequestParam("idUser") String user, @RequestParam("mpId") String mpId) {
		try {
			absenService.insert(user, mpId);
			return new ResponseEntity<>("Berhasil Absen", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Gagal Absen", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("check/kehadiran")
	public ResponseEntity<?> checkAbsen(@RequestParam("idUser") String user, @RequestParam("mpId") String mpId) {
		Boolean cek = false;
		try {
			cek = absenService.checkAbsen(user, mpId);
			return new ResponseEntity<>(cek, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(cek, HttpStatus.BAD_REQUEST);
		}
	}

}
