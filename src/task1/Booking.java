package task1;

import java.util.Date;

public class Booking {
    private final String client;
    private final Date startDate;
    private final Date endDate;
    private final double totalPrice;

    public Booking(String client, Date startDate, Date endDate, double totalPrice) {
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public String getClient() {
        return client;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean overlapsWith(Date start, Date end) {
        return (startDate.before(end) && endDate.after(start));
    }
}

