import java.util.*;

abstract class Product {
    protected String id;
    protected String name;
    protected double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public abstract void displayInfo();

    public String getId() { return id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
}

class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id + " | Tên: " + name + " | Giá: " + price + " | Trọng lượng: " + weight + " kg");
    }
}

class DigitalProduct extends Product {
    private double size;

    public DigitalProduct(String id, String name, double price, double size) {
        super(id, name, price);
        this.size = size;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id + " | Tên: " + name + " | Giá: " + price + " | Dung lượng: " + size + " MB");
    }
}

// Singleton ProductDatabase
class ProductDatabase {
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

// Factory tạo sản phẩm
class ProductFactory {
    public static Product createProduct(int type, String id, String name, double price, double extra) {
        if (type == 1) {
            return new PhysicalProduct(id, name, price, extra);
        } else if (type == 2) {
            return new DigitalProduct(id, name, price, extra);
        }
        return null;
    }
}

// Main
public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            System.out.println("\n---------------------- QUẢN LÝ SẢN PHẨM ----------------------");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xoá sản phẩm");
            System.out.println("5. Thoát");
            System.out.println("-----------------------------------------------------------------------");
            System.out.print("Lựa chọn của bạn: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nhập tên: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập giá: ");
                    double price = sc.nextDouble();
                    System.out.print("Loại sản phẩm (1: Vật lý, 2: Kỹ thuật số): ");
                    int type = sc.nextInt();
                    System.out.print(type == 1 ? "Nhập trọng lượng (kg): " : "Nhập dung lượng (MB): ");
                    double extra = sc.nextDouble();
                    Product p = ProductFactory.createProduct(type, id, name, price, extra);
                    db.addProduct(p);
                    System.out.println("Đã thêm sản phẩm!");
                    break;
                case 2:
                    db.listProducts();
                    break;
                case 3:
                    System.out.print("Nhập ID sản phẩm cần cập nhật: ");
                    String updateId = sc.nextLine();
                    System.out.print("Tên mới: ");
                    String newName = sc.nextLine();
                    System.out.print("Giá mới: ");
                    double newPrice = sc.nextDouble();
                    db.updateProduct(updateId, newName, newPrice);
                    break;
                case 4:
                    System.out.print("Nhập ID sản phẩm cần xoá: ");
                    String removeId = sc.nextLine();
                    db.removeProduct(removeId);
                    break;
                case 5:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
