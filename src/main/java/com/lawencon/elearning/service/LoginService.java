package com.lawencon.elearning.service;

import com.lawencon.elearning.model.Account;
import com.lawencon.elearning.payload.request.SignupRequest;

public interface LoginService {
	void saveAkun(SignupRequest signUp, Account akun) throws Exception;

}
