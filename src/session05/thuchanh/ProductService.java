package session05.thuchanh;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private List<Product> products = new ArrayList<>();

    // Thêm mới (Create): Kiểm tra trùng ID trước khi thêm
    public void addProduct(Product product) throws InvalidProductException {

        boolean exist = products.stream()
                .anyMatch(p -> p.getId() == product.getId());

        if (exist) {
            throw new InvalidProductException("ID sản phẩm đã tồn tại");
        }

        products.add(product);
        System.out.println("Thêm sản phẩm thành công");
    }

    // Hiển thị (Read): Xuất danh sách sản phẩm theo định dạng bảng
    public void displayProducts() {

        if (products.isEmpty()) {
            System.out.println("Danh sách trống");
            return;
        }

        System.out.printf("%-5s %-15s %-10s %-10s %-10s\n", "ID ","Name ","Price ","Quantity ","Category ");

        products.forEach(System.out::println);
    }

    // Cập nhật (Update): Tìm theo ID và cập nhật số lượng. Sử dụng Optional để xử lý kết quả tìm kiếm
    public void updateQuantity(int id, int newQuantity) throws InvalidProductException {

        Optional<Product> productOpt = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (productOpt.isPresent()) {

            productOpt.get().setQuantity(newQuantity);
            System.out.println("Cập nhật thành công");

        } else {
            throw new InvalidProductException("Không tìm thấy sản phẩm");
        }
    }

    // Xóa (Delete): Xóa các sản phẩm có quantity = 0
    public void removeOutOfStock() {

        products.removeIf(p -> p.getQuantity() == 0);

        System.out.println("Đã xóa sản phẩm hết hàng");
    }
}
