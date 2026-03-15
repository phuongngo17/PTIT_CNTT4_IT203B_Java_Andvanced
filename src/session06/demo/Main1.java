package session06.demo;

public class Main1 {
    public static void main(String[] args) {

        Demo1 task = new Demo1();
        Thread thread = new Thread(task);

        thread.start();
    }
}