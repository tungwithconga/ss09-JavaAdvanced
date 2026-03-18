package BaiKiemTra;

import java.util.*;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(String id, String newName, double newPrice) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                p.setName(newName);
                p.setPrice(newPrice);
                System.out.println("Đã cập nhật sản phẩm!");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với ID: " + id);
    }

    public void removeProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
        System.out.println("Đã xoá sản phẩm!");
    }

    public void listProducts() {
        if (products.isEmpty()) {
            System.out.println("Kho hàng trống!");
        } else {
            for (Product p : products) {
                p.displayInfo();
            }
        }
    }
}
