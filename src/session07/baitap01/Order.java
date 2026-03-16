package session07.baitap01;

import java.util.Map;

public class Order {
    private String orderId;
    private Customer customer;
    private Map<Product, Integer> items;
    private double total;

    public Order(String orderId, Customer customer, Map<Product, Integer> items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Product, Integer> getProducts() {
        return items;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.items = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
