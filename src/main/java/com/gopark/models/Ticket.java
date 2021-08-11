package com.gopark.models;

import java.time.LocalDateTime;


public class Ticket
{

    private String floorNumber;
    private String spotNumber;
    private LocalDateTime startTime;

    public String getFloorNumber()
    {
        return floorNumber;
    }


    public void setFloorNumber( String floorNumber )
    {
        this.floorNumber = floorNumber;
    }


    public LocalDateTime getStartTime()
    {
        return startTime;
    }


    public void setStartTime( LocalDateTime startTime )
    {
        this.startTime = startTime;
    }


    public String getSpotNumber()
    {
        return spotNumber;
    }


    public void setSpotNumber( String spotNumber )
    {
        this.spotNumber = spotNumber;
    }


    @Override
    public String toString()
    {
        return "Ticket [floorNumber=" + floorNumber + ", spotNumber=" + spotNumber + ", startTime=" + startTime + "]";
    }


}
