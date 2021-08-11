package com.gopark.models;

import java.util.Objects;

public class ParkingSpot
{

    private String spotNumber;
    private int floorNumber;
    private String type;
    private double ratePerHour;
    private String activeVechileNumber;
    
    public ParkingSpot(String spotNumber, int floorNumber, String type, double rate)
    {
       this.spotNumber = spotNumber;
       this.floorNumber = floorNumber;
       this.type = type;
       this.ratePerHour = rate;
    }

    public String getSpotNumber()
    {
        return spotNumber;
    }


    public void setSpotNumber( String spotNumber )
    {
        this.spotNumber = spotNumber;
    }


    public int getFloorNumber()
    {
        return floorNumber;
    }


    public void setFloorNumber( int floorNumber )
    {
        this.floorNumber = floorNumber;
    }


    public String getType()
    {
        return type;
    }


    public void setType( String type )
    {
        this.type = type;
    }


    public double getRatePerHour()
    {
        return ratePerHour;
    }

    public void setRatePerHour( double ratePerHour )
    {
        this.ratePerHour = ratePerHour;
    }

    @Override
    public String toString()
    {
        return "ParkingSpot [spotNumber=" + spotNumber + ", floorNumber=" + floorNumber + ", type=" + type + "]";
    }

    

    public String getActiveVechileNumber()
    {
        return activeVechileNumber;
    }

    public void setActiveVechileNumber( String activeVechileNumber )
    {
        this.activeVechileNumber = activeVechileNumber;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( spotNumber );
    }


    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        ParkingSpot other = (ParkingSpot) obj;
        return Objects.equals( spotNumber, other.spotNumber );
    }
    

}
