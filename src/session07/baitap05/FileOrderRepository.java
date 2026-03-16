package session07.baitap05;

import java.util.ArrayList;
import java.util.List;

public class FileOrderRepository implements OrderRepository {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
        System.out.println("Đã lưu đơn hàng " + order.getId());
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}