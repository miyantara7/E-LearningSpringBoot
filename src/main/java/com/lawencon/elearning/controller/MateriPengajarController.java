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
import com.lawencon.elearning.model.MateriPengajar;
import com.lawencon.elearning.service.MateriPengajarService;

@RestController
@CrossOrigin("*")
public class MateriPengajarController extends BaseController<MateriPengajar> {

	@Autowired
	private MateriPengajarService materiPengajarService;

	@PostMapping("mp/insert")
	public ResponseEntity<?> insert(@RequestBody String body) {
		try {
			MateriPengajar mp = readValue(body, MateriPengajar.class);
			materiPengajarService.insert(mp);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to insert", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestParam("materi") String materiId,
			@RequestParam("pengajar") String pengajarId) {
		MateriPengajar mp = new MateriPengajar();
		try {
			mp = materiPengajarService.update(pengajarId, materiId);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(mp, HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("id") String id) {
		try {
			materiPengajarService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to delete", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	@GetMapping("/showall")
	public ResponseEntity<?> showAllPengajar() {
		List<MateriPengajar> list = new ArrayList<>();
		try {
			list = materiPengajarService.findAll();
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to show", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/showbyid")
	public ResponseEntity<?> showPengajarById(@RequestParam("id") String id) {
		MateriPengajar mp = new MateriPengajar();
		try {
			mp = materiPengajarService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to delete", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(mp, HttpStatus.OK);
	}

	@GetMapping("/find/pengajar")
	public ResponseEntity<?> findPengajar(@RequestParam("idMateri") String idMateri) {
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			result = materiPengajarService.findMateri(idMateri);
		} catch (Exception e) {
			return new ResponseEntity<>(result, HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/show/class-nilai")
	public ResponseEntity<?> showNilaiKelas(@RequestParam("mpId") String mpId) {
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			result = materiPengajarService.showNilaiKelas(mpId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/show/class-by-trainer")
	public ResponseEntity<?> showMateriPengajarByTrainer(@RequestParam("tId") String tId) {
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			result = materiPengajarService.findMateriPengajarByTrainer(tId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
