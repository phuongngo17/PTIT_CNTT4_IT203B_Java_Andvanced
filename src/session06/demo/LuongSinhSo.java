package session06.demo;

import java.util.Random;

public class LuongSinhSo extends Thread{
    private Datashared datashared;
    public LuongSinhSo(Datashared datashared) {
        this.datashared = datashared;
    }
    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            synchronized (datashared) {
                if (datashared.getControl() == 1) {
                    //nếu control = 1 thì thread này làm
                    int number = random.nextInt(100);
                    datashared.setNumber(number);

                    System.out.println("Thread 1 - sinh số:" + datashared.getNumber() );

                    datashared.setControl(2);
                    datashared.notifyAll();
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    //control !=1 thì thread này phải chờ
                    try {
                        datashared.wait();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public synchronized void sayHello() {
        System.out.println("phương thức được đồng bộ");
    }
}
