package com.gopark.parking.pubsub;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.gopark.models.Vechile;
import com.gopark.parking.pubsub.handler.TopicHandler;
import com.gopark.parking.pubsub.services.ISubscriber;
import com.gopark.parking.pubsub.services.TopicSubscriber;

public class Queue
{
    
    private Map<String, TopicHandler> topicProcessors;
    
    public Queue() {
        this.topicProcessors = new HashMap<>();
    }

    public Topic createTopic( final String topicName) {
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topicProcessors.put(topic.getTopicId(), topicHandler);
        System.out.println("Created topic: " + topic.getTopicName());
        return topic;
    }

    public void subscribe( final ISubscriber subscriber,  final Topic topic) {
        topic.addSubscriber(new TopicSubscriber(subscriber));
        System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
    }

    public void publish( final Topic topic,  final Vechile vechile) {
        topic.addVechile( vechile );
        new Thread(() -> topicProcessors.get(topic.getTopicId()).publish()).start();
    }

    public void resetOffset( final Topic topic,  final ISubscriber subscriber, final Integer newOffset) {
        for (TopicSubscriber topicSubscriber : topic.getSubscribers()) {
            if (topicSubscriber.getSubscriber().equals(subscriber)) {
                topicSubscriber.getOffset().set(newOffset);
                System.out.println(topicSubscriber.getSubscriber().getId() + " offset reset to: " + newOffset);
                new Thread(() -> topicProcessors.get(topic.getTopicId()).startSubsriberWorker(topicSubscriber)).start();
                break;
            }
        }
    }
}
