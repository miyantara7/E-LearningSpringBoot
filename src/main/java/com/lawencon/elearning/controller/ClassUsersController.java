package com.lawencon.elearning.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.model.Class;
import com.lawencon.elearning.model.ClassUsers;
import com.lawencon.elearning.service.ClassUsersService;

@RestController
@CrossOrigin("*")
public class ClassUsersController {

	@Autowired
	private ClassUsersService kelasService;

	@GetMapping("/user/class")
	public ResponseEntity<?> showAllMateri(@RequestParam("uId") String userid,
			@RequestParam("kId") String idMateriPengajar) {
		boolean cek = false;
		try {
			cek = kelasService.findClass(userid, idMateriPengajar);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(cek, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(cek, HttpStatus.OK);
	}

	@PostMapping("/user/inclass")
	public ResponseEntity<?> insertClass(@RequestParam("uId") String userid,
			@RequestParam("kId") String idMateriPengajar) {
		ClassUsers kelas = new ClassUsers();
		try {
			kelas = kelasService.insertKelas(idMateriPengajar, userid);
		} catch (Exception e) {
			return new ResponseEntity<>(kelas, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(kelas, HttpStatus.OK);
	}

	@GetMapping("/pengajar/class")
	public ResponseEntity<?> classPengajar(@RequestParam("mId") String materiId,
			@RequestParam("pId") String pengajarId) {
		List<Map<String, Object>> listClass = new ArrayList<>();
		try {
			listClass = kelasService.listKelas(materiId, pengajarId);
		} catch (Exception e) {
			return new ResponseEntity<>(listClass, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listClass, HttpStatus.OK);
	}

	@GetMapping("/cek/class")
	public ResponseEntity<?> cekClass(@RequestParam("uId") String userId, @RequestParam("waktu") String jam) {
		boolean cek = false;
		try {
			cek = kelasService.cekClass(userId, jam);
		} catch (Exception e) {
			return new ResponseEntity<>(cek, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(cek, HttpStatus.OK);
	}

	@GetMapping("class/view-user")
	public ResponseEntity<?> viewUser(@RequestParam("id") String id) {
		List<Map<String, Object>> listUser = new ArrayList<>();
		try {
			listUser = kelasService.viewUserClass(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listUser, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listUser, HttpStatus.OK);
	}

	@GetMapping("kelas/view-kelas")
	public ResponseEntity<?> viewAllClass() {
		List<Class> listAllClass = new ArrayList<>();
		try {
			listAllClass = kelasService.viewAllClass();
		} catch (Exception e) {
			return new ResponseEntity<>(listAllClass, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listAllClass, HttpStatus.OK);
	}
}
