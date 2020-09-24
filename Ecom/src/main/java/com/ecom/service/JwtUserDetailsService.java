package com.ecom.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.dao.UserDAO;
import com.ecom.domain.DAOUser;




@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DAOUser user = userDao.findByLOGIN_USERNAME(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getCONTACT_NO(), user.getPASSWORD(),
				new ArrayList<>());
	}
	
	
	public UserDetails loadUserByMobile(String username) throws UsernameNotFoundException {
		DAOUser user = userDao.findByMobileNumber(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getCONTACT_NO(), user.getPASSWORD(),
				new ArrayList<>());
	}
	
	
}