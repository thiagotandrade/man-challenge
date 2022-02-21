package eu.man.challenge.shared.entities;

import java.util.Date;

public class ErrorDetails {

    private final Date timestamp;
    private final String message;
    private final String status;

    public ErrorDetails(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = "error";
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
