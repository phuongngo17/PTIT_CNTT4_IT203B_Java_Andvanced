package session06.baitap03;

import session06.baitap01.TicketPool;

public class Main {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A",5);
        TicketPool roomB = new TicketPool("B",5);

        BookingCounter counter1 =
                new BookingCounter("Quầy 1", roomA, roomB,false);

        BookingCounter counter2 =
                new BookingCounter("Quầy 2", roomA, roomB,true);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        t1.start();
        t2.start();
    }
}
