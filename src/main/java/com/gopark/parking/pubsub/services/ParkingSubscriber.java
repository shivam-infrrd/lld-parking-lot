package com.gopark.parking.pubsub.services;

import com.gopark.models.Vechile;
import com.gopark.parking.strategy.ParkingStrategy;

public class ParkingSubscriber implements ISubscriber
{
    private final String id;
    private final int sleepTimeInMillis;
    private ParkingStrategy parkingStrategy;
    
    
    public ParkingSubscriber(ParkingStrategy parkingStrategy, String id, int sleepInMillis)
    {
        this.id = id;
        this.sleepTimeInMillis = sleepInMillis;
        this.parkingStrategy = parkingStrategy;
    }
    
    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public void consume( Vechile vechile ) throws InterruptedException
    {   
        parkingStrategy.reserveSpot( vechile.getTerminal(), vechile.getType(), vechile.getVechileNo() ); 
        Thread.sleep(sleepTimeInMillis); 
        
    }
}
