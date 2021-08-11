package com.gopark.parking.pubsub.services;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber
{
    private AtomicInteger offset;
    private ISubscriber subscriber;
    
    public TopicSubscriber(final ISubscriber subscriber) {
        this.offset = new AtomicInteger(0);
        this.subscriber = subscriber;
    }

    public AtomicInteger getOffset()
    {
        return offset;
    }

    public void setOffset( AtomicInteger offset )
    {
        this.offset = offset;
    }

    public ISubscriber getSubscriber()
    {
        return subscriber;
    }

    public void setSubscriber( ISubscriber subscriber )
    {
        this.subscriber = subscriber;
    }
    
     
}
