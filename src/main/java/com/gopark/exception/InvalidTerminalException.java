package com.gopark.exception;

/**
 * Exception given when the mode cannot be decided for the parking lot program.
 */
public class InvalidTerminalException extends RuntimeException {
    
    public InvalidTerminalException(String message)
    {
        super (message);
    }
}
