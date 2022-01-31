package com.bridgelabz.bookstore.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
	private String firstName;
	private String lastName;
	private String kyc;
	private String emailId;
	private String password;
	private Boolean verify=false;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dob;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate registerDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate updatedDate;
	// private int otp;
//	@JsonFormat(pattern = "dd-MM-yyyy")
//	private LocalDate purchaseDate;
//	@JsonFormat(pattern = "dd-MM-yyyy")
//	private LocalDate expiryDate;
}