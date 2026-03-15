package session06.baitap05;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 8);
        TicketPool roomC = new TicketPool("C", 6);

        BookingCounter c1 = new BookingCounter(roomA);
        BookingCounter c2 = new BookingCounter(roomB);
        BookingCounter c3 = new BookingCounter(roomC);
        BookingCounter c4 = new BookingCounter(roomA);
        BookingCounter c5 = new BookingCounter(roomB);

        TimeoutManager manager =
                new TimeoutManager(Arrays.asList(roomA, roomB, roomC));

        new Thread(c1, "Quầy 1").start();
        new Thread(c2, "Quầy 2").start();
        new Thread(c3, "Quầy 3").start();
        new Thread(c4, "Quầy 4").start();
        new Thread(c5, "Quầy 5").start();

        new Thread(manager, "TimeoutManager").start();
    }
}