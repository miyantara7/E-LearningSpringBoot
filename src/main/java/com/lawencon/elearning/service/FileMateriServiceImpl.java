package com.lawencon.elearning.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lawencon.elearning.dao.FileMateriDao;
import com.lawencon.elearning.dao.MateriHeaderDao;
import com.lawencon.elearning.model.FileMateri;
import com.lawencon.elearning.model.MateriHeader;
import com.lawencon.elearning.model.MateriPengajar;

@Service
@Transactional
public class FileMateriServiceImpl implements FileMateriService {

	@Autowired
	@Qualifier("materi_repo_hibernate")
	private FileMateriDao fileMateriService;

	@Autowired
	private MateriHeaderDao headerService;

	@Override
	public FileMateri insertMateri(MultipartFile file, String topic, String kId, String name, String tanggal,
			String waktu) throws Exception {
		FileMateri materi = new FileMateri();
		String[] format = new String[] { "yyyy-MM-dd" };
		Date date = DateUtils.parseDate(tanggal, format);
		LocalDate localDate = null;
		DateTimeFormatter formatter = null;
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		localDate = LocalDate.parse(tanggal, formatter);
		materi.setTitle(name);
		materi.setTypeFile(file.getContentType());
		materi.setFileMateri(file.getBytes());
		materi.setActiveFlag('Y');
		MateriPengajar mp = new MateriPengajar();
		MateriHeader mHeader = new MateriHeader();
		DayOfWeek hari = LocalDate.parse(tanggal).getDayOfWeek();
		String header = null;
		try {
			header = headerService.findByTopicCategoryTrainer(topic.toUpperCase(), localDate, waktu,
					hari.toString().toUpperCase(), kId);
		} catch (Exception e) {
		}
		if (header == null) {
			mHeader.setTopic(topic.toUpperCase());
			mHeader.setDay(hari.toString().toUpperCase());
			mHeader.setDate(date);
			mHeader.setTime(waktu);
			mp.setId(kId);
			mHeader.setMateriPengajar(mp);
			materi.setHeader(headerService.insertHeader(mHeader));
			return fileMateriService.insertMateri(materi);
		} else {
			mHeader.setHeaderId(header);
			materi.setHeader(mHeader);
			return fileMateriService.insertMateri(materi);
		}
	}

	@Override
	public List<Map<String, Object>> findMateri(String header) throws Exception {
		return fileMateriService.findMateri(header);
	}

	@Override
	public List<Map<String, Object>> findByCategory(String category) throws Exception {
		return fileMateriService.findByCategory(category);
	}

	@Override
	public List<Map<String, Object>> findByCategoryAndTrainer(String category, String trainer) throws Exception {
		return fileMateriService.findByCategoryAndTrainer(category, trainer);
	}

	@Override
	public List<Map<String, Object>> findJumMateri(String headerId) throws Exception {
		return fileMateriService.findJumMateri(headerId);
	}

	@Override
	public List<String> findTopic(String kelasId) throws Exception {
		LocalDate currentDate = LocalDate.now();
		LocalTime timeNow = LocalTime.now();
		DayOfWeek dayNow = currentDate.getDayOfWeek();
		String hari = String.valueOf(dayNow);
		String waktu = String.valueOf(timeNow);
		return fileMateriService.findTopic(kelasId, hari.toUpperCase(), currentDate, waktu);
	}

	@Override
	public List<Map<String, Object>> findFilePengajar(String idPengajar) throws Exception {
		return fileMateriService.findFilePengajar(idPengajar);
	}

	@Override
	public List<Map<String, Object>> findClassPengajar(String idPengajar) throws Exception {
		return fileMateriService.findClassPengajar(idPengajar);
	}

	@Override
	public List<Map<String, Object>> findFileClass(String idMateriPengajar) throws Exception {
		return fileMateriService.findFileClass(idMateriPengajar);
	}

	@Override
	public void deleteFile(String headerId) throws Exception {
		fileMateriService.delete(headerId);

	}

	@Override
	public void update(String id, MultipartFile file, String judul) throws Exception {
		FileMateri fileMateri = null;
		try {
			fileMateri = fileMateriService.findById(id);
			if (fileMateri != null) {
				fileMateri.setFileMateri(file.getBytes());
				fileMateri.setTypeFile(file.getContentType());
				fileMateri.setTitle(judul);
				fileMateriService.updateFile(fileMateri);
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	@Override
	public void deleteFileMateri(String id) throws Exception {
		FileMateri fileMateri = null;
		try {
			fileMateri = fileMateriService.findById(id);
			if (fileMateri != null)
				fileMateriService.deleteFileMateri(fileMateri);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
