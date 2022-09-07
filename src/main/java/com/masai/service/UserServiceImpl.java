package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.CurrentUserSession;
import com.masai.model.User;
import com.masai.repository.UserDao;
import com.masai.repository.UserSessionDAO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Override
	public User createUser(User user) {
		
		Optional<User> opt= userDao.findByMobileNo(user.getMobileNo());
		
		if(opt.isPresent()) {
			System.out.println("User already exist");
		}
		return userDao.save(user);
		
	}

	@Override
	public User updateUser(User user, String key) {
//		User user1= getCurrentUserSession.getCurrentLogInuser(key);
//				if(user1==null) {
//					System.out.println("no user foung");
//					throw new RuntimeException("No user found");
//				}
//		userDao.save(user);
		//System.out.println("out");
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
		
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
			return userDao.save(user);
//			Integer userId= optCurrUser.get().getUserId();
//
//			Optional<User> optUser =userDao.findById(userId);
//			return optUser.get();
		//return user;
	}

}
