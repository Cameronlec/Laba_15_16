package restaurant;

import javax.management.RuntimeErrorException;

public class IllegalTableNumber extends RuntimeErrorException {
    private String details;

    public IllegalTableNumber(String detail, String message) {
        super(new Error(message));
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
