package com.gopark.parking.strategy;

import java.util.Map;

import com.gopark.models.ParkingSpot;

public interface ParkingStrategy
{
    
    /**
     * Add a new slot to parking strategy. After adding, this new slot will become available for
     * future parkings.
     *
     * @param slotNumber Slot number to be added.
     */
    public ParkingSpot reserveSpot(String terminalNumber, String type, String vechileNo);

    /**
     * Unpark slotId after unparking spot is ready to be used by different vehicle
     * @param spotId
     */
    public ParkingSpot releaseSpot(String spotNumber);  
    
    /**
     *  Monitoring function to get status of parking lot
     */
    public Map<String, Integer> activeCounts ();    
    
    /**
     * Find By Parking Spot
     * @param spotNumber
     * @return
     */
    public ParkingSpot findSpotById (String spotNumber);
    
    /**
     * 
     * @param vechileNumber
     * @param spotNumber
     */
    public void updateStatus  (String vechileNumber, String spotNumber);
    
}
