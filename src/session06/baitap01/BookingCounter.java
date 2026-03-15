package session06.baitap01;

import java.util.Random;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount;

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
        this.soldCount = 0;
    }

    public int getSoldCount() {
        return soldCount;
    }

    @Override
    public void run() {

        Random random = new Random();

        while (roomA.remainingTickets() > 0 || roomB.remainingTickets() > 0) {

            Ticket ticket = null;

            if (random.nextBoolean()) {
                System.out.println(counterName + " bán vé phòng A");
                ticket = roomA.sellTicket();
            } else {
                System.out.println(counterName + " bán vé phòng B");
                ticket = roomB.sellTicket();
            }

            if (ticket != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé " + ticket.getTicketId());
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(counterName + " đã hết việc.");
    }
}