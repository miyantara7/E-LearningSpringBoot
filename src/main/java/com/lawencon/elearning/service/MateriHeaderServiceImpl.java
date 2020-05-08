package com.lawencon.elearning.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.MateriHeaderDao;
import com.lawencon.elearning.model.ForumDetail;
import com.lawencon.elearning.model.ForumHeader;
import com.lawencon.elearning.model.MateriHeader;

@Service
@Transactional
public class MateriHeaderServiceImpl implements MateriHeaderService {

	@Autowired
	private MateriHeaderDao materiHeaderDao;

	@Autowired
	private FileMateriService fileMateriService;

	@Autowired
	private ForumService forumService;

	@Override
	public MateriHeader update(String id, String tanggal, String waktu) throws Exception {
		MateriHeader header = null;
		try {
			header = materiHeaderDao.findById(id);
			if (header != null) {
				String[] format = new String[] { "yyyy-MM-dd" };
				Date date = DateUtils.parseDate(tanggal, format);
				header.setDate(date);
				header.setTime(waktu);
				DayOfWeek day = LocalDate.parse(tanggal).getDayOfWeek();
				header.setDay(day.toString().toUpperCase());
				return materiHeaderDao.updateHeader(header);
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception();
		}

	}

	@Override
	public void deleteFileHeader(String headerId) throws Exception {
		ForumHeader headerForum = null;
		List<ForumDetail> listChat = new ArrayList<>();
		try {
			headerForum = forumService.checkHeader(headerId);
			if (headerForum != null) {
				listChat = forumService.findAllChat(headerForum.getId());
				if (!listChat.isEmpty()) {
					listChat.forEach(x -> {
						try {
							forumService.deleteChat(x);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				}
				forumService.deleteHeader(headerForum);
			}
			fileMateriService.deleteFile(headerId);
			materiHeaderDao.deleteHeader(headerId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
