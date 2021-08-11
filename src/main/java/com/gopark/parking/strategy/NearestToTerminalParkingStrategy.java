package com.gopark.parking.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.gopark.config.service.ConfigurationService;
import com.gopark.exception.InvalidTerminalException;
import com.gopark.exception.NoFreeSlotAvailableException;
import com.gopark.models.ParkingConfig;
import com.gopark.models.ParkingSpot;


public class NearestToTerminalParkingStrategy implements ParkingStrategy
{

    private static NearestToTerminalParkingStrategy instance;

    private Map<String, Map<ParkingSpot, Integer>> parkingSpotTerminalWiseDistance;
    private Map<String, Boolean> parkingSpotStatus;

    private ConfigurationService configService = ConfigurationService.getInstance();

    private NearestToTerminalParkingStrategy()
    {

        ParkingConfig parkingConfig = configService.getParkingConfig();

        Map<String, Map<String, Object>> spotDetailMap = parkingConfig.getParkingSpotMap();

        Map<String, Map<String, Integer>> distanceMap = parkingConfig.getDistanceMap();


        parkingSpotTerminalWiseDistance = new HashMap<>();
        distanceMap.forEach( ( k, v ) -> {

            Map<ParkingSpot, Integer> spots = new HashMap<>();
            v.forEach( ( spotNumber, distance ) -> {

                spots.put( new ParkingSpot( spotNumber, (int) spotDetailMap.get( spotNumber ).get( "floorNumber" ),
                    (String) spotDetailMap.get( spotNumber ).get( "type" ), (double) spotDetailMap.get( spotNumber ).get( "rate" )), distance );
            } );
            parkingSpotTerminalWiseDistance.put( k, spots );
        } );
        
        parkingSpotStatus = new HashMap<> ();
        spotDetailMap.forEach( (k, v ) -> {
            parkingSpotStatus.put( k, true );
        });
        
    }


    @Override
    public synchronized ParkingSpot reserveSpot( String terminalNumber, String type, String vechileNo )
    {
            
        Map<ParkingSpot, Integer> spotsOfTerminal = parkingSpotTerminalWiseDistance.get( terminalNumber );
        if (spotsOfTerminal == null)
            throw new InvalidTerminalException("Invalid terminal");
       
        double minDiff = Double.MAX_VALUE;
        ParkingSpot parkingSpot = null;
        
        for (Map.Entry<ParkingSpot, Integer> entry : spotsOfTerminal.entrySet()) {
            if (entry.getKey().getType().equalsIgnoreCase( type ) && parkingSpotStatus.get( entry.getKey().getSpotNumber() ) && entry.getValue() < minDiff ) {
                parkingSpot = entry.getKey();
            }
        }
        
        if( parkingSpot == null) {
            System.out.println ("Sorry! No Free Slot found Plese wait for free slot ");
        }else {
            updateStatus( vechileNo, parkingSpot.getSpotNumber() ); 
        }
       
  
        
        return parkingSpot;
    }


    @Override
    public ParkingSpot releaseSpot( String spotNumber )
    {
        Optional<String> teminal  = parkingSpotTerminalWiseDistance.keySet().stream().findAny();
        Map<ParkingSpot, Integer> allSpots = parkingSpotTerminalWiseDistance.get( teminal.get() ); 
        
        ParkingSpot spot = allSpots.keySet().stream().filter( k -> k.getSpotNumber().equalsIgnoreCase( spotNumber ) ).findAny().get();
        
        parkingSpotStatus.computeIfPresent( spotNumber, (k, v) -> v = true );     
        
        return spot;
    }




    @Override
    public Map<String, Integer> activeCounts()
    {
        Optional<String> teminal  = parkingSpotTerminalWiseDistance.keySet().stream().findAny();
        Map<ParkingSpot, Integer> allSpots = parkingSpotTerminalWiseDistance.get( teminal.get() ); 
        
        Map<String, Integer> typeBasedCount = new HashMap<>();
        allSpots.forEach( (k, v) -> {
            
            if (parkingSpotStatus.get( k.getSpotNumber() )) {
                typeBasedCount.computeIfAbsent( k.getType(), val ->  0);
                
                typeBasedCount.put( k.getType(), typeBasedCount.get( k.getType() ) +1 );
            }
         
        });
        
        return typeBasedCount;
    }
    
    @Override
    public ParkingSpot findSpotById (String spotNumber) {
        Optional<String> teminal  = parkingSpotTerminalWiseDistance.keySet().stream().findAny();
        Map<ParkingSpot, Integer> allSpots = parkingSpotTerminalWiseDistance.get( teminal.get() ); 
        
        return allSpots.keySet().stream().filter( k -> k.getSpotNumber().equalsIgnoreCase( spotNumber ) ).findAny().get();
    }

   
    @Override
    public void updateStatus  (String vechileNumber, String spotNumber) {
        
        ParkingSpot spot  = findSpotById( spotNumber );
        spot.setActiveVechileNumber( vechileNumber );
        
        parkingSpotStatus.put( spotNumber, false );
        
        System.out.println( "***** Your vechile is Parked. Your Ticket ********* " );
        System.out.println( "\nVechile Number : "+spot.getActiveVechileNumber()+ "\nSpot Number : "+spot.getSpotNumber() +"\nFloor No"+spot.getFloorNumber() );
        
        Map<String, Integer> activeCounts = activeCounts();
        
        System.out.println(  "\n\n \t============= Monitoring Panel ========== " );
        activeCounts.forEach( (k, v) -> {
           System.out.println( "Type : "+k + " Available count - " +v );
        });
    }
    
    public static NearestToTerminalParkingStrategy getInstance()
    {
        if ( instance == null ) {
            instance = new NearestToTerminalParkingStrategy();
        }
        return instance;
    }


}