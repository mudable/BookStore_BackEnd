package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.model.OrderData;

public interface OrderRepository extends JpaRepository<OrderData, Integer> {
	@Query(value = "SELECT * FROM order_details WHERE userfk=:id", nativeQuery = true)
	List<OrderData> findUserOrder(int id);

	@Query(value = "SELECT * FROM order_details WHERE cancel=:cancel", nativeQuery = true)
	List<OrderData> findorder(boolean cancel);
}
