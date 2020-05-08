package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Map;

public interface ReportDao {
	List<?> nilaiAkhirDetail(String userId, String mpId, String classId) throws Exception;
	
	List<?> nilaiAkhirDetailByUser(String userId) throws Exception;
	
	List<?> nilaiAkhir(String userId) throws Exception;

	List<?> findAllJadwal() throws Exception;
	
	List<Map<String, Object>> getJadwalPeriode(String tgl1, String tgl2) throws Exception;
	
	List<?> findByClassMateri(String classId, String materiPengajarId) throws Exception;
	
	List<?> findAllMapel() throws Exception;
	
	List<?> findAllKelas() throws Exception;
	
	List<?> findPesertaClassByMateriClass(String materiId, String classId) throws Exception;
	
	List<?> findMateriByUser(String userId) throws Exception;
	
	List<?> findKelasByUser(String userId) throws Exception;
	
	List<?> nilaiPesertaByMPClass(String materiId, String classId) throws Exception;
	
	List<?> rincianNilai(String materiId, String classId, String period) throws Exception;

	List<?> findKehadiran(String materiId, String classId) throws Exception;

	
}
