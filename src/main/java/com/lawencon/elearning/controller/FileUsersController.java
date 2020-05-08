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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.lawencon.elearning.model.FileUsers;
import com.lawencon.elearning.service.FileUsersService;

@RestController
@CrossOrigin("*")
public class FileUsersController {

	@Autowired
	private FileUsersService fileUjianService;

	@PostMapping("file-user/upload-ujian")
	public ResponseEntity<?> uploadTask(@RequestParam("file") MultipartFile file,
			@RequestParam("idHeader") String idSoalHeader, @RequestParam("idUser") String idUser) {
		boolean pesan = false;
		try {
			pesan = fileUjianService.insertUjian(file, idSoalHeader, idUser);
			return new ResponseEntity<>(pesan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(pesan, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("file-user/download")
	public ResponseEntity<?> downloadTask(@RequestParam("id") String id) {
		FileUsers fileUjian = new FileUsers();
		try {
			fileUjian = fileUjianService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileUjian.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileUjian.getFileName() + "\"")
				.body(fileUjian.getFile());
	}

	@PostMapping("file-user/upload-kuis")
	public ResponseEntity<?> insertTask(@RequestParam("idUser") String idUser,
			@RequestParam("idHeader") String idHeader, @RequestParam("file") MultipartFile file) {
		boolean pesan = false;
		try {
			pesan = fileUjianService.insertTask(idUser, idHeader, file);
			return new ResponseEntity<>(pesan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(pesan, HttpStatus.BAD_REQUEST);

	}

	@GetMapping("file-user/view-file")
	public ResponseEntity<?> viewFile(@RequestParam("kId") String kelasId, @RequestParam("uId") String userId) {
		List<Map<String, Object>> listfile = new ArrayList<>();
		try {
			listfile = fileUjianService.viewFileUser(kelasId, userId);
			return new ResponseEntity<>(listfile, HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>(listfile, HttpStatus.BAD_REQUEST);
	}

}
