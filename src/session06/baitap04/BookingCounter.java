package session06.baitap04;

import session06.baitap01.Ticket;
import java.util.Random;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;

    public BookingCounter(String counterName,
                          TicketPool roomA,
                          TicketPool roomB) {

        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    @Override
    public void run() {

        Random random = new Random();

        while (true) {

            Ticket ticket;

            if (random.nextBoolean()) {

                ticket = roomA.sellTicket();

            } else {

                ticket = roomB.sellTicket();
            }

            if (ticket != null) {

                System.out.println(counterName +
                        " bán vé " + ticket.getTicketId());
            }

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}