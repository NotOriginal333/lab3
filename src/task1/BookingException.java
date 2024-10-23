package task1;

public class BookingException extends Exception {
    private String cottageName;
    private String clientName;

    public BookingException(String message) {
        super(message);
    }

    public BookingException(String message, String cottageName) {
        super(message);
        this.cottageName = cottageName;
    }

    public BookingException(String message, String cottageName, String clientName) {
        super(message);
        this.cottageName = cottageName;
        this.clientName = clientName;
    }

    public String getCottageName() {
        return cottageName;
    }

    public String getClientName() {
        return clientName;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder(super.getMessage());
        if (getCottageName() != null) {
            message.append(" [Cottage: ").append(getCottageName()).append("]");
        }
        if (getClientName() != null) {
            message.append(" [Client: ").append(getClientName()).append("]");
        }
        return message.toString();
    }
}
