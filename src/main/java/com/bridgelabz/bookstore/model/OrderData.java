package com.bridgelabz.bookstore.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OrderDetails")
public class OrderData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderId;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate orderDate;

	private int price;
	private int quantity;
	private String address;

	@ManyToOne
	@JoinColumn(name = "userfk", referencedColumnName = "userId")
	private UserRegistrationData userData;

	@ManyToOne
	@JoinColumn(name = "bookfk", referencedColumnName = "bookId")
	private BookDetails bookDetails;

	private boolean cancel = false;

	public void CreateOrder(OrderDTO orderdto) {
		this.address = orderdto.getAddress();
		this.orderDate = LocalDate.now();
		this.price = orderdto.getPrice();
		this.quantity = orderdto.getQuantity();
		this.cancel = false;
	}
}