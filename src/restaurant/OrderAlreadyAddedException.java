package restaurant;

public class OrderAlreadyAddedException extends Exception{
    private String details;
    public OrderAlreadyAddedException(String detail, String message) {
        super(message);
        this.details = detail;
    }

    @Override
    public String toString() {
        return "MyException{"
                + "detail=" + details
                + ", message=" + getMessage()
                + "} ";
    }
}
