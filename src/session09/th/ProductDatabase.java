package session09.th;

import java.util.ArrayList;
import java.util.List;

import static session07.baitap05.DataStore.products;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> productList;
    private ProductDatabase(){
        productList = new ArrayList<>();
    }

    public static ProductDatabase getInstance(){
        if(instance == null){
            instance = new ProductDatabase();
        }
        return instance;
    }
    public void addProduct(Product product) {
        productList.add(product);
    }
    public List<Product> getProductList(){
        return productList;
    }
    public void  deleteProduct(String id) {
        productList.removeIf(p ->p.getId().equals(id));
    }
    public Product getProduct(String id) {
        for (Product p : productList) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
