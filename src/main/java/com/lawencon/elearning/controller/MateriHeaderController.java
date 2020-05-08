package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.service.MateriHeaderService;

@RestController
@CrossOrigin("*")
@RequestMapping("materi-header")
public class MateriHeaderController {

	@Autowired
	private MateriHeaderService headerService;

	@PostMapping("/update")
	public ResponseEntity<?> updateHeader(@RequestParam("id") String id, @RequestParam("tanggal") String tanggal,
			@RequestParam("waktu") String waktu) {
		try {
			headerService.update(id, tanggal, waktu);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<?> deleteHeaderFile(@RequestParam("id") String id) {
		try {
			headerService.deleteFileHeader(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
