package session06.baitap05;

import java.util.Random;

public class BookingCounter implements Runnable {

    private TicketPool pool;
    private Random random = new Random();

    public BookingCounter(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {

        while (true) {

            boolean vip = random.nextBoolean();

            Ticket t = pool.holdTicket(vip);

            if (t != null) {

                try {
                    Thread.sleep(3000);
                } catch (Exception e) {}

                pool.sellHeldTicket(t);
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    }
}
