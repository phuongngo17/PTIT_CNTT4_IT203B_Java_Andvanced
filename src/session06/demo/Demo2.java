package session06.demo;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Demo2 implements Runnable {

    List<String> names = Arrays.asList(
            "An", "Bình", "Chi", "Dũng", "Hà", "Lan", "Nam", "Phương"
    );

    List<String> hometowns = Arrays.asList(
            "Hà Nội", "Hải Phòng", "Nam Định", "Thanh Hóa",
            "Nghệ An", "Đà Nẵng", "Huế", "Quảng Ninh"
    );

    Random random = new Random();

    @Override
    public void run() {

        while (true) {

            int index = random.nextInt(names.size());

            String name = names.get(index);
            String hometown = hometowns.get(index);

            System.out.println("Tên: " + name + " | Quê quán: " + hometown);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}