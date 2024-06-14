package com.lcwd.UserServic.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Builder
@Data

public class ErrorDetails {

    private String details;
    private Date date;
    private String message;

    public ErrorDetails(String details, Date date, String message) {
        this.details = details;
        this.date = date;
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }



}
