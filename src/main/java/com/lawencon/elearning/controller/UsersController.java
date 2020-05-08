package com.lawencon.elearning.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.elearning.model.Users;
import com.lawencon.elearning.service.UserService;

@RestController
@CrossOrigin("*")
public class UsersController extends BaseController<Users> {

	@Autowired
	private UserService userService;

	private Users user = new Users();

	@GetMapping("/user")
	public ResponseEntity<?> getUser() {
		try {
			List<Map<String, Object>> listUsers = userService.getUsers();
			return new ResponseEntity<>(listUsers, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Menampilkan Users", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/userid")
	public ResponseEntity<?> getById(@RequestParam("id") String id) {
		try {
			Map<String, Object> user = userService.getById(id).get(0);
			return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Menampilkan Users", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody String content) {
		try {
			user = super.readValue(content, Users.class);
			return new ResponseEntity<>(userService.createUser(user), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Gagal Menambah User", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody String content) {
		try {
			user = super.readValue(content, Users.class);
			return new ResponseEntity<>(userService.updateUser(user), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Gagal Mengubah Users", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/user")
	public ResponseEntity<?> deleteUser(@RequestParam("id") String id) {
		try {
			return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Menghapus Users", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("user/jadwal")
	public ResponseEntity<?> getJadwal(@RequestParam("kId") String kelasId, @RequestParam("uId") String userId) {
		List<Map<String, Object>> listjadwal = new ArrayList<>();
		try {
			listjadwal = userService.getJadwal(kelasId, userId);
			return new ResponseEntity<>(listjadwal, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listjadwal, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("user/kelas")
	public ResponseEntity<?> getKelas(@RequestParam("uId") String userId) {
		List<Map<String, Object>> listkelas = new ArrayList<>();
		try {
			listkelas = userService.getKelasUser(userId);
			return new ResponseEntity<>(listkelas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listkelas, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("user/nilai-tugas")
	public ResponseEntity<?> getNilaiTugasUser(@RequestParam("uId") String userId, @RequestParam("mpId") String mpId) {
		List<Map<String, Object>> listkelas = new ArrayList<>();
		try {
			listkelas = userService.showNilaiTugasUser(mpId, userId);
			return new ResponseEntity<>(listkelas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listkelas, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("user/nilai-ujian")
	public ResponseEntity<?> getNilaiUjianUser(@RequestParam("uId") String userId, @RequestParam("mpId") String mpId) {
		List<Map<String, Object>> listkelas = new ArrayList<>();
		try {
			listkelas = userService.showNilaiUjianUser(mpId, userId);
			return new ResponseEntity<>(listkelas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listkelas, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("user/jadwal-test")
	public ResponseEntity<?> getJadwalTest(@RequestParam("uId") String userId, @RequestParam("mpId") String mpId) {
		List<Map<String, Object>> listkelas = new ArrayList<>();
		try {
			listkelas = userService.getJadwalTest(userId, mpId);
			return new ResponseEntity<>(listkelas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(listkelas, HttpStatus.BAD_REQUEST);
		}
	}

}
