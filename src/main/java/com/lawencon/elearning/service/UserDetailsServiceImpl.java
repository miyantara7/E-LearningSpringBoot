package com.lawencon.elearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.elearning.dao.LoginDao;
import com.lawencon.elearning.dao.UserRepository;
import com.lawencon.elearning.model.Admin;
//import com.lawencon.elearning.model.Admin;
import com.lawencon.elearning.model.Account;
import com.lawencon.elearning.model.Trainer;
import com.lawencon.elearning.model.Users;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginDao login;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = userRepository.findByEmail(username);
		if (user == null) {
			user = userRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		}
		Users temp = null;
		Trainer pengajar = null;
		Admin admin = null;
		String nama = "";
		String id = "";
		try {
			temp = login.findUserById(user.getAccountId());
			if (temp != null) {
				nama = temp.getName();
				id = temp.getId();
			} else {
				pengajar = login.findPengajarById(user.getAccountId());
				if (pengajar != null) {
					nama = pengajar.getName();
					id = pengajar.getTrainerId();
				} else {
					admin = login.findAdminById(user.getAccountId());
					if (admin != null) {
						nama = admin.getName();
						id = admin.getAdminId();
					} else {
						throw new Exception();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return UserDetailsImpl.build(user, nama, id);
	}

}
