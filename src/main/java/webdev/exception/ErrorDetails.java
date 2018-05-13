package webdev.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ErrorDetails {
    @Getter
    @Setter
    private Boolean success;
    @Getter
    @Setter
    private Date timestamp;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.success = false;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
