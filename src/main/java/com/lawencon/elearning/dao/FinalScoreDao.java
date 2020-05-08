package com.lawencon.elearning.dao;

import com.lawencon.elearning.model.FinalScore;

public interface FinalScoreDao {

	int getNilaiUjianByUser(String idUser, String mpId) throws Exception;

	Long getNilaiTugasByUser(String idUser, String mpId) throws Exception;

	int getCountSoalTugas(String mpId) throws Exception;

	int getCountSoalUjian(String mpId) throws Exception;

	void insert(FinalScore nilaiTotal) throws Exception;

	void update(FinalScore nilaiTotal) throws Exception;

	FinalScore checkNilai(String idUser, String mpId) throws Exception;
	
	Double checkNilaibyMId(String idUser, String mId, String cId) throws Exception;
}
