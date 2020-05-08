package com.lawencon.elearning.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface ReportService {
	String downloadNilaiAkhirDetail(HttpServletResponse response, String userId, String mpId, String classId)
			throws Exception;

	String downloadNilaiAkhir(HttpServletResponse response, String userId) throws Exception;

	String downloadFindAllJadwal(HttpServletResponse response) throws Exception;

	String downloadJadwalPeriode(String tgl1, String tgl2, HttpServletResponse response) throws Exception;

	String downloadFindByClassMateri(String classId, String materiPengajarId, HttpServletResponse response)
			throws Exception;

	String downloadFindAllMapel(HttpServletResponse response) throws Exception;

	String downloadPesertaClassByMateriClass(String materiId, String classId, HttpServletResponse response)
			throws Exception;

	String nilaiPesertaByMPClass(String materiId, String classId, HttpServletResponse response) throws Exception;

	String downloadRincianNilai(String materiId, String classId, HttpServletResponse response, String period)
			throws Exception;

	String downloadKehadiran(String materiId, String classId, HttpServletResponse response) throws Exception;

	List<?> findKehadiran(String materiId, String classId) throws Exception;

	List<?> findAllJadwal() throws Exception;

	List<?> findAllKelas() throws Exception;

	List<?> findPesertaByMateriClass(String materiId, String classId) throws Exception;

	List<Map<String, Object>> getJadwalPeriode(String tgl1, String tgl2) throws Exception;

	List<?> findByClassMateri(String classId, String materiPengajarId) throws Exception;

	List<?> findAllMapel() throws Exception;

	List<?> findMateriByUser(String userId) throws Exception;

	List<?> findKelasByUser(String userId) throws Exception;

	List<?> nilaiAkhirDetail(String userId, String mpId, String classId) throws Exception;

	List<?> nilaiAkhirDetailByUser(String userId) throws Exception;

	List<?> rincianNilai(String materiId, String classId, String period) throws Exception;

}
