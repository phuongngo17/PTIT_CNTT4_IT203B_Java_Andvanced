package session07.baitap05;

import java.util.List;

public class Order {
    private String id;
    private Customer customer;
    private List<OrderItem> items;
    private double finalAmount;

    public Order(String id, Customer customer, List<OrderItem> items, double finalAmount) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.finalAmount = finalAmount;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getFinalAmount() {
        return finalAmount;
    }
}