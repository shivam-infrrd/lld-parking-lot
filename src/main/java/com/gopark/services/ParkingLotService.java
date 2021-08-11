package com.gopark.services;

import java.util.List;
import java.util.UUID;

import com.gopark.models.ParkingSpot;
import com.gopark.models.Vechile;
import com.gopark.parking.pubsub.Queue;
import com.gopark.parking.pubsub.Topic;
import com.gopark.parking.pubsub.services.ParkingSubscriber;
import com.gopark.parking.strategy.NearestToTerminalParkingStrategy;
import com.gopark.parking.strategy.ParkingStrategy;


/**
 * Service for enable the functioning of a parking lot. This will have all the business logic of
 * how the parking service will operate.
 */
public class ParkingLotService
{

    private ParkingStrategy parkingStrategy;
    private Queue queue;
    private Topic vechileTopic;

    public ParkingLotService()
    {
        this.queue = new Queue();
        this.vechileTopic = queue.createTopic( "vehicle" );

        parkingStrategy = NearestToTerminalParkingStrategy.getInstance();
        final ParkingSubscriber parkingSubscriber = new ParkingSubscriber( parkingStrategy, UUID.randomUUID().toString(),
            10000 );
        queue.subscribe( parkingSubscriber, vechileTopic );
        
    }


    public void park( Vechile vehicle )
    {
        queue.publish( vechileTopic, vehicle );
    }


    public void unPark( String spotNumber )
    {

        ParkingSpot spot = parkingStrategy.releaseSpot( spotNumber );
        
        System.out.println( "\nVechile Number "+ spot.getActiveVechileNumber() +" has unparked and Slot "+ spot.getSpotNumber()+" is Free" );
        
        List<Vechile> vechiles = vechileTopic.getVechiles();
        
        Vechile vechile = null;
        for (int index = vechiles.size()-1; index>=0 ; index--) {
           
            if (vechiles.get( index ).getType().equalsIgnoreCase( spot.getType() )) {
                vechile = vechiles.get( index );
                break;
            }
        }
        
        if (vechile != null) {
            parkingStrategy.updateStatus( vechile.getVechileNo(), spotNumber );
        }
        
    }

}