package com.bridgelabz.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserRegistrationDTO;
import com.bridgelabz.bookstore.model.UserRegistrationData;

@Service
public interface IUserRegistration {
	public ResponseDTO createUserData(UserRegistrationDTO userdto);

	public List<UserRegistrationData> getAllUsers();

	public ResponseDTO updateUserData(int userId, UserRegistrationDTO userdto);

	public ResponseDTO deleteUserData(int userId);

	public UserRegistrationData getUserDataById(int userId);

	public ResponseDTO loginValidation(LoginDTO logindto);

	public Boolean verifyUser(String token);
}
