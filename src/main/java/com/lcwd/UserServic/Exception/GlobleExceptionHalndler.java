package com.lcwd.UserServic.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobleExceptionHalndler {
    private WebRequest webRequest;

    public GlobleExceptionHalndler(WebRequest webRequest) {
        this.webRequest = webRequest;
    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleresourceNotFound(ResourceNotFound resourceNotFound)
    {
//        String message = resourceNotFound.getMessage();
//        ErrorDetails.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();

        ErrorDetails errorDetails = new ErrorDetails(resourceNotFound.getMessage(), new Date(), webRequest.getDescription(false));


        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);


    }
}
