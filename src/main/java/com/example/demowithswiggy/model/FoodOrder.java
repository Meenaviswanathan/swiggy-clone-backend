package com.example.demowithswiggy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

@ManyToOne
@JsonIgnore   // 👈 ADD THIS
private User customer;

@ManyToOne
@JsonIgnore   // 👈 ADD THIS
private User deliveryPartner;
        @ManyToMany
    private List<FoodItem> items;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

	public FoodOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FoodOrder(User customer, User deliveryPartner, List<FoodItem> items, OrderStatus status) {
		super();
		this.customer = customer;
		this.deliveryPartner = deliveryPartner;
		this.items = items;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public User getDeliveryPartner() {
		return deliveryPartner;
	}

	public void setDeliveryPartner(User deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}

	public List<FoodItem> getItems() {
		return items;
	}

	public void setItems(List<FoodItem> items) {
		this.items = items;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
