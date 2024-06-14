package com.lcwd.UserServic.Exception;

public class ResourceNotFound extends RuntimeException
{
    public ResourceNotFound() {
        super("Invalid input value");
    }

    public ResourceNotFound(String message) {
        super(message);
    }
}
