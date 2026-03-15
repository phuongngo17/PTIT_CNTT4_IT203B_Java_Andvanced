package session06.baitap05;

public class Ticket {

    private String ticketId;
    private boolean sold;
    private boolean held;
    private boolean vip;
    private long holdExpiryTime;

    public Ticket(String ticketId, boolean vip) {
        this.ticketId = ticketId;
        this.vip = vip;
    }

    public String getTicketId() {
        return ticketId;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public boolean isHeld() {
        return held;
    }

    public void setHeld(boolean held) {
        this.held = held;
    }

    public boolean isVIP() {
        return vip;
    }

    public long getHoldExpiryTime() {
        return holdExpiryTime;
    }

    public void setHoldExpiryTime(long holdExpiryTime) {
        this.holdExpiryTime = holdExpiryTime;
    }
}