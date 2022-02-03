package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.exception.UserRegistrationException;
import com.bridgelabz.bookstore.model.BookDetails;
import com.bridgelabz.bookstore.model.CartData;
import com.bridgelabz.bookstore.model.OrderData;
import com.bridgelabz.bookstore.model.UserRegistrationData;
import com.bridgelabz.bookstore.repository.BookDetailsRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRegistrationRepository;
import com.bridgelabz.bookstore.tokenutil.TokenUtil;

@Service
public class OrderService implements IOrderService {
	@Autowired
	private TokenUtil tokenutil;
	@Autowired
	private UserRegistrationRepository repository;
	@Autowired
	private BookDetailsRepository bookrepo;
	@Autowired
	private OrderRepository orderrepo;

	@Override
	public OrderData createOrderData(String token, OrderDTO orderdto, int bookId) {
		int id = tokenutil.decodeToken(token);
		Optional<UserRegistrationData> user = repository.findById(id);
		Optional<BookDetails> book = bookrepo.findById(bookId);
		OrderData orderdata = new OrderData();
		if (user.isPresent() && book.isPresent()) {
			orderdata.setUserData(user.get());
			orderdata.setBookDetails(book.get());
			orderdata.CreateOrder(orderdto);
			return orderrepo.save(orderdata);

		} else {
			throw new UserRegistrationException("userid or bookid is invalid");

		}
	}

	@Override
	public OrderData cancelOrder(String token, int orderId) {
		int id = tokenutil.decodeToken(token);
		Optional<UserRegistrationData> user = repository.findById(id);
		if (user.isPresent()) {
			OrderData order = orderrepo.getById(orderId);
			order.setCancel(true);
			return orderrepo.save(order);
		} else {
			throw new UserRegistrationException("Invalid order Id");
		}

	}

	@Override
	public List<OrderData> getAllOrdersOfUser(String token) {
		int id = tokenutil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = repository.findById(id);
		if (isPresent.isPresent()) {
			List<OrderData> orderData = orderrepo.findUserOrder(id);
			return orderData;
		} else {
			return null;
		}

	}

	@Override
	public List<OrderData> FindAllOrders() {
		boolean cancel = false;
		return orderrepo.findorder(cancel);

	}

}
