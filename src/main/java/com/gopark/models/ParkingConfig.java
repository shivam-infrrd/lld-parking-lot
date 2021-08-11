package com.gopark.models;

import java.util.Map;

public class ParkingConfig
{

    private int numberOfTerminals;
    private Map<String, Map<String, Integer>> distanceMap;
    
    // contains parking spot with per hour charge, floor number, type
    private Map<String, Map<String, Object>> parkingSpotMap;

    public int getNumberOfTerminals()
    {
        return numberOfTerminals;
    }

    public void setNumberOfTerminals( int numberOfTerminals )
    {
        this.numberOfTerminals = numberOfTerminals;
    }

    public Map<String, Map<String, Integer>> getDistanceMap()
    {
        return distanceMap;
    }

    public void setDistanceMap( Map<String, Map<String, Integer>> distanceMap )
    {
        this.distanceMap = distanceMap;
    }

    public Map<String, Map<String, Object>> getParkingSpotMap()
    {
        return parkingSpotMap;
    }

    public void setParkingSpotMap( Map<String, Map<String, Object>> parkingSpotMap )
    {
        this.parkingSpotMap = parkingSpotMap;
    }
    
}
