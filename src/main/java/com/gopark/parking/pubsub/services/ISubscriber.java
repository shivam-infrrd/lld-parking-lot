package com.gopark.parking.pubsub.services;

import com.gopark.models.Vechile;

public interface ISubscriber
{
    String getId();
    void consume(Vechile vechile) throws InterruptedException;
}
