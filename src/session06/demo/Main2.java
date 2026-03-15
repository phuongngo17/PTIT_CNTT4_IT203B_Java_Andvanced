package session06.demo;

public class Main2 {
    public static void main(String[] args) {

        Demo2 task = new Demo2();

        Thread thread = new Thread(task);

        thread.start();
    }
}
