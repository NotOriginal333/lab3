package task1;

import java.util.Calendar;

public class Booking {
    private final String client;
    private final Calendar startDate;
    private final Calendar endDate;
    private final double totalPrice;

    public Booking(String client, Calendar startDate, Calendar endDate, double totalPrice) {
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public String getClient() {
        return client;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean overlapsWith(Calendar start, Calendar end) {
        return (startDate.before(end) || endDate.after(start));
    }
}

