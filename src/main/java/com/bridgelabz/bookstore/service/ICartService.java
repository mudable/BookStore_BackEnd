package com.bridgelabz.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.model.CartData;
@Service

public interface ICartService {

	CartData addToCart(String token, int bookId, CartDTO cartdto);

	void removeCart(int cartId);

	CartData updateCartQuentity(String token, int cartId, int quantity);

	List<CartData> getAllCart(String token);

}
