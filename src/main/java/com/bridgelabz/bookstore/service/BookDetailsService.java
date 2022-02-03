package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.bookstore.dto.BookDetailsDTO;
import com.bridgelabz.bookstore.exception.UserRegistrationException;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.model.UserRegistrationData;
import com.bridgelabz.bookstore.repository.BookDetailsRepository;
import com.bridgelabz.bookstore.repository.UserRegistrationRepository;
import com.bridgelabz.bookstore.tokenutil.TokenUtil;

@Service

public class BookDetailsService implements IBookDetailsService {
	@Autowired
	BookDetailsRepository bookRepo;

	@Autowired
	UserRegistrationRepository userRepo;

	@Autowired
	UserRegistrationService service;

	@Autowired
	TokenUtil tokenUtil;

	@Override
	public List<BookDetails> showAllBooks(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);
		if (isPresent.isPresent()) {
			return bookRepo.findAll();
		} else {
			return null;
		}
	}

	@Override
	public BookDetails getBookById(String token, int bookId) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);
		if (isPresent.isPresent()) {
			return bookRepo.findById(bookId).orElseThrow(() -> new UserRegistrationException("Book Not Found"));
		}
		return null;
	}

	@Override
	public BookDetails updateBook(String token, int bookId, BookDetailsDTO bookdto) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);
		if (isPresent.isPresent()) {
			BookDetails book = this.getBookById(token, bookId);
			book.updateBookData(bookdto);
			return bookRepo.save(book);
		} else {
			throw new UserRegistrationException("User NOT present");
		}
	}

	@Override
	public void deleteBook(String token, int bookId) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);
		if (isPresent.isPresent()) {
			BookDetails book = this.getBookById(token, bookId);
			bookRepo.delete(book);
		}
	}

	@Override
	public BookDetails addBook(String token, BookDetailsDTO bookDto) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);
		if (isPresent.isPresent()) {
			BookDetails book = new BookDetails();
			book.createBook(bookDto);
			bookRepo.save(book);
			return book;

		} else
			return null;
	}

	@Override
	public BookDetails updateBookQuantity(String token, int bookId, int bookQuantity) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);
		if (isPresent.isPresent()) {
			BookDetails book = this.getBookById(token, bookId);
			book.setBookQuantity(bookQuantity);
			bookRepo.save(book);
			return book;

		} else {
			return null;
		}
	}

	@Override
	public BookDetails updateBookPrice(String token, int bookId, int bookPrice) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepo.findById(id);
		if (isPresent.isPresent()) {
			BookDetails book = this.getBookById(token, bookId);
			book.setBookPrice(bookPrice);
			bookRepo.save(book);
			return book;
		} else {
			return null;
		}

	}

}