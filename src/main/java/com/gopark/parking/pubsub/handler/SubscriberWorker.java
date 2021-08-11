package com.gopark.parking.pubsub.handler;

import com.gopark.models.Vechile;
import com.gopark.parking.pubsub.Topic;
import com.gopark.parking.pubsub.services.TopicSubscriber;


public class SubscriberWorker implements Runnable
{

    private Topic topic;
    private TopicSubscriber topicSubscriber;

    public SubscriberWorker(  final Topic topic,  final TopicSubscriber topicSubscriber )
    {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    
    @Override
    public void run()
    {
        synchronized ( topicSubscriber ) {
            do {
                int curOffset = topicSubscriber.getOffset().get();
                while ( curOffset >= topic.getVechiles().size() ) {
                    try {
                        topicSubscriber.wait();
                    } catch ( InterruptedException e ) {
                        e.printStackTrace();
                    }
                }
                Vechile vechile = topic.getVechiles().get( curOffset );
                try {
                    topicSubscriber.getSubscriber().consume( vechile );
                } catch ( InterruptedException e ) {
                   
                    e.printStackTrace();
                }

                topicSubscriber.getOffset().compareAndSet( curOffset, curOffset + 1 );
            } while ( true );
        }
    }
    
    synchronized public void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
