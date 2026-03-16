package session07.baitap04;

import java.util.List;

public interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}
