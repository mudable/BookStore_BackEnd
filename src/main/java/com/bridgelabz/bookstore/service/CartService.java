package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.model.CartData;
import com.bridgelabz.bookstore.model.UserRegistrationData;
import com.bridgelabz.bookstore.repository.BookDetailsRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRegistrationRepository;
import com.bridgelabz.bookstore.tokenutil.TokenUtil;

@Service
public class CartService implements ICartService {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	BookDetailsRepository bookrepo;
	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	UserRegistrationRepository userRepository;

	@Override
	public CartData addToCart(String token, int bookId, CartDTO cartdto) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if (isPresent.isPresent()) {
			BookDetails books = bookrepo.getById(bookId);
			CartData cart = new CartData(isPresent.get(), books, cartdto.getQuantity());
			return cartRepository.save(cart);
		} else {
			return null;
		}
	}

	@Override
	public void removeCart(int cartId) {
		cartRepository.deleteById(cartId);

	}

	@Override
	public CartData updateCartQuentity(String token, int cartId, int quantity) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if (isPresent.isPresent()) {
			CartData cart = cartRepository.getById(cartId);
			cart.setQuantity(quantity);
			return cartRepository.save(cart);
		} else {
			return null;
		}

	}

	@Override
	public List<CartData> getAllCart(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if (isPresent.isPresent()) {
			List<CartData> cartProduct = cartRepository.findAll();
			return cartProduct;
		} else {
			return null;
		}

	}
}
