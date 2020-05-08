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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.elearning.model.Class;
import com.lawencon.elearning.service.ClassService;

@RestController
@CrossOrigin("*")
public class ClassController extends BaseController<Class> {

	@Autowired
	private ClassService kelasPengajarService;

	@PostMapping("kelas-pengajar/insert")
	public ResponseEntity<?> insertKelas(@RequestBody String content) {
		Class kelas = new Class();
		boolean message = false;
		try {
			kelas = readValue(content, Class.class);
			message = kelasPengajarService.insert(kelas);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PostMapping("kelas-pengajar/update")
	public ResponseEntity<?> updateKelas(@RequestBody String content) {
		try {
			Class kelas = readValue(content, Class.class);
			kelasPengajarService.update(kelas);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@PostMapping("kelas-pengajar/delete")
	public ResponseEntity<?> delete(@RequestParam("id") String id) {
		try {
			kelasPengajarService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to delete", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@GetMapping("kelas-pengajar/findbyid")
	public ResponseEntity<?> findById(@RequestParam("id") String id) {
		Class kelas = new Class();
		try {
			kelas = kelasPengajarService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(kelas, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(kelas, HttpStatus.OK);
	}

	@GetMapping("kelas-pengajar/findall")
	public ResponseEntity<?> findAll() {
		List<Class> list = new ArrayList<Class>();
		try {
			list = kelasPengajarService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("kelas-pengajar/show-by-trainer")
	public ResponseEntity<?> showByTrainer(@RequestParam("tId") String tId) {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			list = kelasPengajarService.findClassesByTrainer(tId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
