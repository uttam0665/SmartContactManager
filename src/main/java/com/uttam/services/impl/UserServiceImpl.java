package com.uttam.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uttam.entities.User;
import com.uttam.helper.AppConstant;
import com.uttam.helper.ResourceNotFoundException;
import com.uttam.repositories.UserRepo;
import com.uttam.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public User saveUser(User user) {

		String userId=UUID.randomUUID().toString();
		user.setUserId(userId);

		//password encode
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//set user role
		
		user.setRoleList(List.of(AppConstant.ROLE_USER));
		
		//default value we can pass like profile picture,..	
		// user.setProfilePic("");	
		return userRepo.save(user);
	}

	@Override
	public Optional<User> getUserById(String id) {
		return userRepo.findById(id);
	}

	// my logic
	@Override
	public Optional<User> updateUser(User user) {
		User getUser = userRepo.findById(user.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User Not Foud"));
				
		getUser.setName("Sambhu Gouda");
		getUser.setPhoneNumber("8984704682");
		getUser.setEmail(user.getEmail());
		getUser.setProfilePic(user.getProfilePic());
		getUser.setPassword(user.getPassword());
		getUser.setAbout(user.getAbout());
		getUser.setEnabled(user.isEnabled());
		getUser.setEmailVerified(user.isEmailVerified());
		getUser.setPhoneVerified(user.isPhoneVerified());
		getUser.setProvider(user.getProvider());
		getUser.setProviderUserId(user.getProviderUserId());
		


		User save = userRepo.save(getUser);
		return Optional.ofNullable(save);
	}

	@Override
	public void deleteUser(String id) {
		User getUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User notfound"));
		
		userRepo.delete(getUser);
	}

	@Override
	public boolean isUserExit(String userId) {
		User user = userRepo.findById(userId).orElse(null);
		
		return user!=null ? true : false;
	}

	@Override
	public boolean isUserExitsByEmail(String email) {
		User user=userRepo.findByEmail(email).orElse(null);
		return user!=null ? true : false;
	}

	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

}
