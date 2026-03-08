package session01.baitap.baitap06;

import java.io.IOException;

public class UserService {

    public void saveToFile() throws IOException {

        System.out.println("Đang lưu dữ liệu vào file...");

        throw new IOException("Lỗi ghi file!");
    }
}
