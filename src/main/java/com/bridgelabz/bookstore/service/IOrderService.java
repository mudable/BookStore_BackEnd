package com.bridgelabz.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.OrderDTO;

import com.bridgelabz.bookstore.model.OrderData;

@Service
public interface IOrderService {

	OrderData createOrderData(String token, OrderDTO orderdto, int bookId);

	OrderData cancelOrder(String token, int orderId);

	List<OrderData> getAllOrdersOfUser(String token);

	List<OrderData> FindAllOrders();

}
