package com.bridgelabz.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstore.model.BookDetails;

public interface BookDetailsRepository  extends JpaRepository<BookDetails, Integer> {

	Optional<BookDetails> findById(int bookid);

}
