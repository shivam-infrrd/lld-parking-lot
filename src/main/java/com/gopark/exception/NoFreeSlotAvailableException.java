package com.gopark.exception;

/**
 * Exception given when parking lot is full and we still try to park a car into it.
 */
public class NoFreeSlotAvailableException extends ParkingLotException {
    
    public NoFreeSlotAvailableException(String message)
    {
        super (message);
    }
}
