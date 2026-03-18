package BaiKiemTra;
import java.util.Scanner;

public class Main {
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
