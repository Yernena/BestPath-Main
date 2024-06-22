package main.java.com.bestpath.models;

public class Order {

    private Integer orderId;
    private Customer customer;
    private Restaurant restaurant;

    public Order(Integer orderId, Customer customer, Restaurant restaurant) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public Order(Restaurant restaurant, Customer customer) {
        this.restaurant=restaurant;
        this.customer=customer;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", customer=").append(customer);
        sb.append(", restaurant=").append(restaurant);
        sb.append('}');
        return sb.toString();
    }
}
