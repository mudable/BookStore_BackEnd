package com.bridgelabz.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserRegistrationDTO;
import com.bridgelabz.bookstore.model.UserRegistrationData;
import com.bridgelabz.bookstore.service.IUserRegistration;
import com.bridgelabz.bookstore.service.UserRegistrationService;

@RestController
@RequestMapping("/userregistration")
public class UserRegistrationController {
	@Autowired
	private UserRegistrationService userservice;

	@GetMapping("/getall")
	ResponseEntity<List<?>> getAllUsers() {
		List<UserRegistrationData> response = userservice.getAllUsers();
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createUserData(@Valid @RequestBody UserRegistrationDTO userdto) {
		ResponseDTO userData = null;
		userData = userservice.createUserData(userdto);
		ResponseDTO respDTO = new ResponseDTO("Create Employee PayrollData:", userData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<ResponseDTO> updateUserData(@Valid @RequestBody UserRegistrationDTO userdto,
			@PathVariable("userId") int userId) {
		ResponseDTO respDTO = userservice.updateUserData(userId, userdto);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ResponseDTO> deleteUserData(@PathVariable("userId") int userId) {
		userservice.deleteUserData(userId);
		ResponseDTO respDTO = new ResponseDTO("Deleted Successful,Deleted Id:", userId);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<ResponseDTO> getUserData(@PathVariable("userId") int userId) {
		UserRegistrationData userData = userservice.getUserDataById(userId);
		ResponseDTO respDTO = new ResponseDTO("Get call for ID Successful:", userData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> addUserRegistrationData(@RequestBody LoginDTO loginDTO) {
		ResponseDTO response = userservice.loginValidation(loginDTO);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@PostMapping("/user/verify/{token}")
	public Boolean login(@PathVariable String token) {
		Boolean result = userservice.verifyUser(token);
		return result;
	}

	@PostMapping("/forgotpassword")
	ResponseEntity<ResponseDTO> forgotpass(@RequestParam String emailId, @RequestParam String password) {
		UserRegistrationData forgotPassword = userservice.forgotPassword(emailId, password);
		ResponseDTO response = new ResponseDTO("Reset Password   :", forgotPassword);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

}
