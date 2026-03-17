package session08.baitap01;

public class HardwareConnection {
    private static HardwareConnection instance;
    private HardwareConnection() {}

    public static HardwareConnection getInstance() {
        if (instance == null) {
            instance = new HardwareConnection();
            System.out.println("HardwareConnection: Đã kết nối phần cứng.");
        }
        return instance;
    }

    public void connect() {
        System.out.println("Đang sử dụng kết nối...");
    }

    public void disconnect() {
        System.out.println("Ngắt kết nối.");
    }
}
