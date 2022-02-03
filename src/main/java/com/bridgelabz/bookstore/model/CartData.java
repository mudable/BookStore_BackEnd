package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addcart")
public class CartData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "userfkeyid", referencedColumnName = "userId")
	private UserRegistrationData user;

	@ManyToOne
	@JoinColumn(name = "bookfkeyid", referencedColumnName = "bookId")
	private BookDetails book;

	private int quantity;

	public CartData(UserRegistrationData user, BookDetails book, int quantity) {
		this.user = user;
		this.book = book;
		this.quantity = quantity;
	}
}