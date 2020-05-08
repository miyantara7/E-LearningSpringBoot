package com.lawencon.elearning.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.lawencon.elearning.dao.FileQuestionDao;
import com.lawencon.elearning.model.ForumQuiz;
import com.lawencon.elearning.model.FileQuestion;
import com.lawencon.elearning.model.FileQuestionHeader;
import com.lawencon.elearning.model.ForumQuizHeader;
import com.lawencon.elearning.model.JenisFile;
import com.lawencon.elearning.model.MateriPengajar;

@Service
@Transactional
public class FileQuestionServiceImpl implements FileQuestionService {

	@Autowired
	private FileQuestionDao fileSoalService;

	@Autowired
	private FileQuestionHeaderService fileSoalHeaderService;

	@Autowired
	private FileUsersService fileuserservice;

	@Autowired
	private ForumService forumService;

	@Override
	public FileQuestion insert(MultipartFile file, String materiPengajar, String jenis, String startDate,
			String endDate, String start, String end, String judulSoal) throws Exception {
		String[] format = new String[] { "yyyy-MM-dd" };
		Date startdate = DateUtils.parseDate(startDate, format);
		Date enddate = DateUtils.parseDate(endDate, format);
		LocalTime starttime = LocalTime.parse(start);
		LocalTime endTime = LocalTime.parse(end);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileQuestion fileNew = new FileQuestion();
		FileQuestionHeader fileHeader = new FileQuestionHeader();
		MateriPengajar mp = new MateriPengajar();
		String headerSoal = null;
		fileNew.setFile(file.getBytes());
		fileNew.setFileName(fileName);
		fileNew.setFileType(file.getContentType());
		fileNew.setTitle(file.getOriginalFilename());
		DayOfWeek day = LocalDate.parse(startDate).getDayOfWeek();
		if (jenis.equalsIgnoreCase(JenisFile.TUGAS.name())) {
			try {
				headerSoal = fileSoalService.findHeaderIdTugas(materiPengajar, day.toString().toUpperCase());
			} catch (Exception e) {
				throw new Exception();
			}
		} else {
			try {
				headerSoal = fileSoalService.findHeaderIdUjian(materiPengajar, day.toString().toUpperCase(), startdate,
						enddate);
			} catch (Exception e) {
				throw new Exception();
			}
		}
		if (headerSoal == null) {
			fileHeader.setStartDate(startdate);
			fileHeader.setEndDate(enddate);
			fileHeader.setDay(day.toString().toUpperCase());
			mp.setId(materiPengajar);
			fileHeader.setType(jenis.toUpperCase());
			fileHeader.setStartTime(starttime);
			fileHeader.setEndTime(endTime);
			fileHeader.setMateriPengajar(mp);
			fileHeader.setTitle(judulSoal.toUpperCase());
			fileNew.setFileQuestionHeader(fileSoalHeaderService.insertHeaderSoal(fileHeader));
			return fileSoalService.insert(fileNew);
		} else {
			fileHeader.setHeaderQuestionId(headerSoal);
			fileNew.setFileQuestionHeader(fileHeader);
			return fileSoalService.insert(fileNew);
		}
	}

	@Override
	public List<Map<String, Object>> findById(String id) throws Exception {
		return fileSoalService.findById(id);
	}

	@Override
	public FileQuestion findSoal(String idMateriPengajar) throws Exception {
		return fileSoalService.findSoal(idMateriPengajar);
	}

	@Override
	public List<Map<String, Object>> findDateTask(String idMateriPengajar) throws Exception {
		LocalDate tanggal = LocalDate.now();
		LocalTime waktu = LocalTime.now();
		return fileSoalService.findDateTask(tanggal, idMateriPengajar, waktu);
	}

	@Override
	public List<Map<String, Object>> findJumSoal(String hsId) throws Exception {
		return fileSoalService.findJumSoal(hsId);
	}

	@Override
	public List<Map<String, Object>> findDateExam(String idMateriPengajar) throws Exception {
		LocalDate tanggal = LocalDate.now();
		LocalTime waktu = LocalTime.now();
		return fileSoalService.findDateExam(waktu, idMateriPengajar, tanggal);
	}

	@Override
	public String updateHeader(String idHeader, String startDate, String endDate, String startTime, String endTime)
			throws Exception {
		FileQuestionHeader header = null;
		String[] format = new String[] { "yyyy-MM-dd" };
		Date tanggalMulai = DateUtils.parseDate(startDate, format);
		Date tanggalAkhir = DateUtils.parseDate(endDate, format);
		LocalTime starttime = LocalTime.parse(startTime);
		LocalTime endtime = LocalTime.parse(endTime);
		DayOfWeek day = LocalDate.parse(startDate).getDayOfWeek();
		try {
			header = fileSoalHeaderService.findFileHeaderById(idHeader);
			if (header != null) {
				header.setStartDate(tanggalMulai);
				header.setEndDate(tanggalAkhir);
				header.setDay(day.toString().toUpperCase());
				header.setStartTime(starttime);
				header.setEndTime(endtime);
				fileSoalHeaderService.updateHeader(header);
				return "Berhasil Update";
			} else {
				return "Gagal Update";
			}
		} catch (Exception e) {
			throw new Exception();
		}

	}

	@Override
	public String updateSoal(String id, MultipartFile file, String judul) throws Exception {
		FileQuestion soal = null;
		try {
			soal = fileSoalService.findFileById(id);
			if (soal != null) {
				soal.setFile(file.getBytes());
				soal.setFileName(file.getOriginalFilename());
				soal.setFileType(file.getContentType());
				soal.setTitle(judul);
				fileSoalService.updateSoal(soal);
				return "Berhasil Update";
			} else {
				return "Gagal Update";
			}
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public String updateSoalUjian(FileQuestionHeader header) throws Exception {
		FileQuestionHeader soalHeader = null;
		DayOfWeek day = LocalDate.parse(header.getStartDate().toString()).getDayOfWeek();
		try {
			soalHeader = fileSoalHeaderService.findFileHeaderById(header.getHeaderQuestionId());
			if (soalHeader != null) {
				soalHeader.setStartDate(header.getStartDate());
				soalHeader.setEndDate(header.getEndDate());
				soalHeader.setStartTime(header.getStartTime());
				soalHeader.setEndTime(header.getEndTime());
				soalHeader.setDay(day.toString().toUpperCase());
				fileSoalHeaderService.updateHeader(soalHeader);
				return "Berhasil Update";
			} else {
				return "Gagal Update";
			}
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public void deleteFileHeader(String id) throws Exception {
		ForumQuizHeader headerForum = null;
		List<ForumQuiz> listChat = new ArrayList<>();
		try {
			try {
				headerForum = forumService.checkHeaderSoal(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (headerForum != null) {
				try {
					listChat = forumService.findAllChatSoal(headerForum.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (!listChat.isEmpty()) {
					listChat.forEach(x -> {
						try {
							forumService.deleteChatSoal(x);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
					forumService.deleteHeaderSoal(headerForum);
				}
			}
			fileuserservice.deleteFileUserByHeaderId(id);
			fileSoalService.deleteFileSoalByHeaderId(id);
			fileSoalHeaderService.deleteFileHeader(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFileSoalById(String id) throws Exception {
		FileQuestion filesoal = null;
		try {
			filesoal = fileSoalService.findFileById(id);
			if (filesoal != null)
				fileSoalService.deleteFileSoalById(filesoal);
		} catch (Exception e) {
			throw new Exception();
		}

	}
}
