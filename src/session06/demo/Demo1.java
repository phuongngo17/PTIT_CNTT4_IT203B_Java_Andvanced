package session06.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Demo1 implements Runnable {
    List<String> student = Arrays.asList("Lan", "Hoa", "Hiếu","Trung","Cao");

    Random random = new Random();

    @Override
    public void run() {
        while (true) {
            int index = random.nextInt(student.size());

            System.out.println("Tên được chọn: " + student.get(index));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
