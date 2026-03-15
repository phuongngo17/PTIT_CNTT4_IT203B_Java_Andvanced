package session06.baitap05;

import java.util.List;

public class TimeoutManager implements Runnable {

    private List<TicketPool> pools;

    public TimeoutManager(List<TicketPool> pools) {
        this.pools = pools;
    }

    @Override
    public void run() {

        while (true) {

            for (TicketPool p : pools) {
                p.releaseExpiredTickets();
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    }
}