package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.CartData;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.service.ICartService;
import com.bridgelabz.bookstore.tokenutil.TokenUtil;

@RestController
@RequestMapping("/cart")

public class CartController {
	@Autowired
	private ICartService cartService;

	@Autowired
	private CartRepository cartrepository;

	@PostMapping("/addCart/{bookId}")
	ResponseEntity<ResponseDTO> addToCart(@RequestHeader(name = "token") String token, @PathVariable int bookId,
			@RequestBody CartDTO cartdto) {
		CartData add = cartService.addToCart(token, bookId, cartdto);
		ResponseDTO response = new ResponseDTO("Product Added To Cart ", add);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/remove/{cartId}")
	ResponseEntity<ResponseDTO> removeFromCart(@PathVariable("cartId") int cartId) {
		cartService.removeCart(cartId);
		ResponseDTO response = new ResponseDTO("item Removed From Cart ", "deleted id:" + cartId);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@PutMapping("/update/{cartId}/{quantity}")
	ResponseEntity<ResponseDTO> updateCartQuentity(@RequestHeader(name = "token") String token,
			@PathVariable("cartId") int cartId, @PathVariable("quantity") int quantity) {
		CartData cart = cartService.updateCartQuentity(token, cartId, quantity);
		ResponseDTO response = new ResponseDTO(" Quantity updated From Cart ", cart);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@GetMapping("/getCart")
	ResponseEntity<ResponseDTO> getAllCartsByUser(@RequestHeader(name = "token") String token) {
		List<CartData> allItemsForUser = cartService.getAllCart(token);
		ResponseDTO response = new ResponseDTO(" Get All product in Cart for user ", allItemsForUser);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllCart")
	ResponseEntity<ResponseDTO> getAllCarts() {
		List<CartData> allItems = cartrepository.findAll();
		ResponseDTO response = new ResponseDTO("All Items in Cart ", allItems);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

}
