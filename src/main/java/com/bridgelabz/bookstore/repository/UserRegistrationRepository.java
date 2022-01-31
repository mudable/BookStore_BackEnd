package com.bridgelabz.bookstore.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.bookstore.model.UserRegistrationData;

public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, Integer> {

	// @Query(value = "select * from userregistration where email_Id= :emailId",
	// nativeQuery = true)

	Optional<UserRegistrationData> findByEmailId(String emailId);

}
