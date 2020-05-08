package com.lawencon.elearning.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.elearning.model.ForumDetail;
import com.lawencon.elearning.model.ForumQuiz;
import com.lawencon.elearning.service.ForumService;

@RestController
@CrossOrigin("*")
public class ForumController {
	
	@Autowired
	private ForumService forumService;
	
	@PostMapping("send/chat")
	public ResponseEntity<?> sendChat(@RequestParam("topic") String topicId,
			@RequestParam("sender") String sender, @RequestParam("isiPesan") String isiPesan) {
		try {
			forumService.insertChat(topicId, sender, isiPesan);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Kirim Pesan", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Berhasil kirim Pesan", HttpStatus.OK);
	}
	
	@GetMapping("get/chat")
	public ResponseEntity<?> getChat(@RequestParam("topic") String topicId) {
		List<ForumDetail> list = new ArrayList<>();
		try {
			list = forumService.findChatByHeaderId(topicId);
		} catch (Exception e) {
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("send/chat-soal")
	public ResponseEntity<?> sendChatSoal(@RequestParam("topic") String topicId,
			@RequestParam("sender") String sender, @RequestParam("isiPesan") String isiPesan) {
		try {
			forumService.insertChatSoal(topicId, sender, isiPesan);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Gagal Kirim Pesan", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Berhasil kirim Pesan", HttpStatus.OK);
	}
	
	@GetMapping("get/chat-soal")
	public ResponseEntity<?> getChatSoal(@RequestParam("topic") String topicId) {
		List<ForumQuiz> list = new ArrayList<>();
		try {
			list = forumService.findChatByHeaderSoalId(topicId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
