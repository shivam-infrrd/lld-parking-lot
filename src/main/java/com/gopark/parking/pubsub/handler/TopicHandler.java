package com.gopark.parking.pubsub.handler;

import java.util.HashMap;
import java.util.Map;

import com.gopark.parking.pubsub.Topic;
import com.gopark.parking.pubsub.services.TopicSubscriber;

public class TopicHandler
{
    
    private Topic topic;
    private Map<String, SubscriberWorker> subscriberWorkers;
    
    public TopicHandler( final Topic topic) {
        this.topic = topic;
        subscriberWorkers = new HashMap<>();
    }

    public void publish() {
        for (TopicSubscriber topicSubscriber:topic.getSubscribers()) {
            startSubsriberWorker(topicSubscriber);
        }
    }

    public void startSubsriberWorker( final TopicSubscriber topicSubscriber) {
        final String subscriberId = topicSubscriber.getSubscriber().getId();
        if (!subscriberWorkers.containsKey(subscriberId)) {
            final SubscriberWorker subscriberWorker = new SubscriberWorker(topic, topicSubscriber);
            subscriberWorkers.put(subscriberId, subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        final SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberId);
        subscriberWorker.wakeUpIfNeeded();
    }
}
