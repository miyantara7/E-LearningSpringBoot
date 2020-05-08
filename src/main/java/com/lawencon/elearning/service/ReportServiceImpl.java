package com.lawencon.elearning.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lawencon.elearning.dao.ClassDao;
import com.lawencon.elearning.dao.FinalScoreDao;
import com.lawencon.elearning.dao.MateriDao;
import com.lawencon.elearning.dao.ReportDao;
import com.lawencon.elearning.model.Class;
import com.lawencon.elearning.model.Materi;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportDao reportDao;

	@Autowired
	ClassDao classDao;

	@Autowired
	MateriDao materiDao;

	@Autowired
	FinalScoreDao finalScoreDao;

	private String generateJasper(List<?> temp, String pathJasper, String nameFile, HttpServletResponse response,
			Map<String, Object> param) throws Exception {
		try {
			File file = ResourceUtils.getFile(pathJasper);
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(temp, false);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);

			JRPdfExporter pdfExp = new JRPdfExporter();
			pdfExp.setExporterInput(new SimpleExporterInput(jasperPrint));
			ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
			pdfExp.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
			pdfExp.exportReport();

			response.setContentType("application/pdf");
			response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
			response.addHeader("Content-Disposition", nameFile);

			java.io.OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(pdfReportStream.toByteArray());
			responseOutputStream.close();
			pdfReportStream.close();
			return "Berhasil Generate File";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String downloadNilaiAkhirDetail(HttpServletResponse response, String id, String mpId, String classId)
			throws Exception {
		List<?> list = new ArrayList<>();
		try {
			Double nilaiAkhir = finalScoreDao.checkNilaibyMId(id, mpId, classId);
			Class kelas = classDao.findById(classId);
			list = reportDao.nilaiAkhirDetail(id, classId, mpId);
			JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(list);

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("nilaiAkhir", nilaiAkhir);
			param.put("kelas", kelas.getName());
			param.put("tabel", item);

			return generateJasper(list, "classpath:Nilai Akhir Detail.jrxml",
					"inline; filename=Laporan Nilai Akhir Detail.pdf;", response, param);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String downloadFindAllJadwal(HttpServletResponse response) throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportDao.findAllJadwal();
			JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(list);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("tabel", item);
			return generateJasper(list, "classpath:Pengajar.jrxml", "inline; filename=Laporan Pengajar.pdf;", response,
					param);

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String downloadJadwalPeriode(String tgl1, String tgl2, HttpServletResponse response) throws Exception {
		List<?> temp = new ArrayList<>();
		try {
			temp = reportDao.getJadwalPeriode(tgl1, tgl2);
			JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(temp);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("startdate", tgl1);
			param.put("enddate", tgl2);
			param.put("tabel", item);

			return generateJasper(temp, "classpath:Jadwal Periode.jrxml", "inline; filename=Jadwal Periode.pdf;",
					response, param);

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String downloadFindByClassMateri(String classId, String materiPengajarId, HttpServletResponse response)
			throws Exception {
		List<?> temp = new ArrayList<>();
		try {
			com.lawencon.elearning.model.Class kelas = classDao.findById(classId);
			Materi m = materiDao.findById(materiPengajarId);
			temp = reportDao.findByClassMateri(classId, materiPengajarId);
			JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(temp);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("kelas", kelas.getName());
			param.put("materi", m.getName());
			param.put("tabel", item);
			return generateJasper(temp, "classpath:Find By Class Materi.jrxml",
					"inline; filename=Pelajaran by Kelas dan Materi.pdf;", response, param);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String downloadFindAllMapel(HttpServletResponse response) throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportDao.findAllMapel();
			JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(list);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("tabel", item);
			return generateJasper(list, "classpath:Find All Mapel.jrxml",
					"inline; filename=Jadwal Materi Pelajaran.pdf;", response, param);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public List<?> findAllJadwal() throws Exception {
		return reportDao.findAllJadwal();
	}

	@Override
	public List<Map<String, Object>> getJadwalPeriode(String tgl1, String tgl2) throws Exception {
		return reportDao.getJadwalPeriode(tgl1, tgl2);
	}

	@Override
	public List<?> findByClassMateri(String classId, String materiPengajarId) throws Exception {
		return reportDao.findByClassMateri(classId, materiPengajarId);
	}

	@Override
	public List<?> findAllMapel() throws Exception {
		return reportDao.findAllMapel();
	}

	@Override
	public String downloadNilaiAkhir(HttpServletResponse response, String userId) throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportDao.nilaiAkhir(userId);

			List<?> temp = reportDao.nilaiAkhir(userId);
			JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(temp);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("tabel", item);

			return generateJasper(list, "classpath:Nilai Akhir.jrxml", "inline; filename=Nilai Akhir.pdf;", response,
					param);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String downloadPesertaClassByMateriClass(String materiId, String classId, HttpServletResponse response)
			throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportDao.findPesertaClassByMateriClass(materiId, classId);
			Materi materi = materiDao.findById(materiId);
			Class kelas = classDao.findById(classId);
			JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(list);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("materi", materi.getName());
			param.put("kelas", kelas.getName());
			param.put("tabel", item);
			return generateJasper(list, "classpath:Peserta By Materi Class.jrxml",
					"inline; filename=Peserta By Materi Class.pdf;", response, param);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String nilaiPesertaByMPClass(String materiId, String classId, HttpServletResponse response)
			throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportDao.nilaiPesertaByMPClass(materiId, classId);
			Materi materi = materiDao.findById(materiId);
			Class kelas = classDao.findById(classId);

			JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(list);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("materi", materi.getName());
			param.put("kelas", kelas.getName());
			param.put("tabel", item);

			System.out.println(list);
			System.out.println(materi.getName());
			System.out.println(kelas.getName());

			return generateJasper(list, "classpath:Nilai Peserta by Materi Pengajar.jrxml",
					"inline; filename=Nilai Peserta by Materi Pengajar.pdf;", response, param);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String downloadKehadiran(String materiId, String classId, HttpServletResponse response) throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportDao.findKehadiran(materiId, classId);
			Materi materi = materiDao.findById(materiId);
			Class kelas = classDao.findById(classId);

			JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(list);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("materi", materi.getName());
			param.put("kelas", kelas.getName());
			param.put("tabel", item);

			return generateJasper(list, "classpath:Kehadiran.jrxml", "inline; filename=Kehadiran.pdf;", response,
					param);

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String downloadRincianNilai(String materiId, String classId, HttpServletResponse response, String period)
			throws Exception {
		List<?> list = new ArrayList<>();
		try {
			list = reportDao.rincianNilai(materiId, classId, period);
			Materi materi = materiDao.findById(materiId);
			Class kelas = classDao.findById(classId);

			JRBeanCollectionDataSource item = new JRBeanCollectionDataSource(list);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("kelas", kelas.getName());
			param.put("materi", materi.getName());
			param.put("tabel", item);
			param.put("period", period);

			return generateJasper(list, "classpath:Rincian Nilai.jrxml", "inline; filename=Rincian Nilai.pdf;",
					response, param);

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public List<?> findAllKelas() throws Exception {
		return reportDao.findAllKelas();
	}

	@Override
	public List<?> findPesertaByMateriClass(String materiId, String classId) throws Exception {
		return reportDao.findPesertaClassByMateriClass(materiId, classId);
	}

	@Override
	public List<?> findMateriByUser(String userId) throws Exception {
		return reportDao.findMateriByUser(userId);
	}

	@Override
	public List<?> findKelasByUser(String userId) throws Exception {
		return reportDao.findKelasByUser(userId);
	}

	@Override
	public List<?> nilaiAkhirDetail(String userId, String mpId, String classId) throws Exception {
		return reportDao.nilaiAkhirDetail(userId, mpId, classId);
	}

	@Override
	public List<?> nilaiAkhirDetailByUser(String userId) throws Exception {
		return reportDao.nilaiAkhirDetailByUser(userId);
	}

	@Override
	public List<?> rincianNilai(String materiId, String classId, String period) throws Exception {
		return reportDao.rincianNilai(materiId, classId, period);
	}

	@Override
	public List<?> findKehadiran(String materiId, String classId) throws Exception {
		return reportDao.findKehadiran(materiId, classId);
	}

}
