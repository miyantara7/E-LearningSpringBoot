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
import com.lawencon.elearning.model.FileMateri;
import com.lawencon.elearning.service.FileMateriService;

@RestController
@CrossOrigin("*")
public class FileMateriController extends BaseController<FileMateri> {

	@Autowired
	private FileMateriService materiService;

	@PostMapping("file-materi/upload")
	public ResponseEntity<?> uploadMateri(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("topic") String topic, @RequestParam("kId") String kId,
			@RequestParam("tanggal") String tanggal, @RequestParam("waktu") String waktu) throws Exception {
		try {
			materiService.insertMateri(file, topic, kId, name, tanggal, waktu);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("file-materi/download")
	public ResponseEntity<?> getMateri(@RequestParam("headerfile") String header) {
		List<Map<String, Object>> listMateri = new ArrayList<>();
		try {
			listMateri = materiService.findMateri(header);
		} catch (Exception e) {
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(listMateri.get(0).get("type").toString()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + listMateri.get(0).get("judul") + "\"")
				.body(listMateri.get(0).get("file"));
	}

	@GetMapping("/materi/jum")
	public ResponseEntity<?> getAllMateri(@RequestParam("headerId") String headerId) {
		List<Map<String, Object>> listJumMateri = new ArrayList<>();
		try {
			listJumMateri = materiService.findJumMateri(headerId);
			return new ResponseEntity<>(listJumMateri, HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>(listJumMateri, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/topic")
	public ResponseEntity<?> getTopic(@RequestParam("kId") String kelasId) {
		List<String> listopic = new ArrayList<>();
		try {
			listopic = materiService.findTopic(kelasId);
			return new ResponseEntity<>(listopic, HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>(listopic, HttpStatus.OK);
	}

	@GetMapping("find/file")
	public ResponseEntity<?> findFilePengajar(@RequestParam("pId") String pId) {
		List<Map<String, Object>> listopic = new ArrayList<>();
		try {
			listopic = materiService.findFilePengajar(pId);
			return new ResponseEntity<>(listopic, HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>(listopic, HttpStatus.OK);
	}

	@GetMapping("find/class")
	public ResponseEntity<?> findClassPengajar(@RequestParam("pId") String pId) {
		List<Map<String, Object>> listopic = new ArrayList<>();
		try {
			listopic = materiService.findClassPengajar(pId);
			return new ResponseEntity<>(listopic, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(listopic, HttpStatus.OK);
	}

	@GetMapping("class/file")
	public ResponseEntity<?> findClassFile(@RequestParam("kId") String kId) {
		List<Map<String, Object>> listopic = new ArrayList<>();
		try {
			listopic = materiService.findFileClass(kId);
			return new ResponseEntity<>(listopic, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listopic, HttpStatus.OK);
		}

	}

	@PostMapping("file/update")
	public ResponseEntity<?> updateFile(@RequestParam("id") String id, @RequestParam("file") MultipartFile file,
			@RequestParam("judul") String judul) {
		try {
			materiService.update(id, file, judul);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("file-materi/delete")
	public ResponseEntity<?> updateFile(@RequestParam("id") String id) {
		try {
			materiService.deleteFileMateri(id);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}

}
