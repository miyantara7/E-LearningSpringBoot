package com.lawencon.elearning.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.elearning.model.Admin;
import com.lawencon.elearning.service.AdminService;

@RestController
public class AdminController extends BaseController<Admin> {

	@Autowired
	private AdminService adminService;

	private Admin admin = new Admin();

	@GetMapping("/admin")
	public ResponseEntity<?> getAdmin() {
		try {
			List<Map<String, Object>> listAdmin = adminService.getAdmin();
			return new ResponseEntity<>(listAdmin, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Menampilkan Admin", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/adminid")
	public ResponseEntity<?> getById(@RequestParam("id") String id) {
		try {
			Map<String, Object> admin = adminService.getById(id).get(0);
			return new ResponseEntity<>(admin, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Menampilkan Admin", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/admin")
	public ResponseEntity<?> createUser(@RequestBody String content) {
		try {
			admin = super.readValue(content, Admin.class);
			return new ResponseEntity<>(adminService.createAdmin(admin), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Menambah Admin", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/admin")
	public ResponseEntity<?> updateAdmin(@RequestBody String content) {
		try {
			admin = super.readValue(content, Admin.class);
			return new ResponseEntity<>(adminService.updateAdmin(admin), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Mengubah Admin", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/admin")
	public ResponseEntity<?> deleteAdmin(@RequestParam("id") String id) {
		try {
			return new ResponseEntity<>(adminService.deleteAdmin(id), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Menghapus Admin", HttpStatus.BAD_REQUEST);
		}
	}

}
