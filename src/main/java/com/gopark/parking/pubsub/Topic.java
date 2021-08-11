package com.gopark.parking.pubsub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gopark.models.Vechile;
import com.gopark.parking.pubsub.services.TopicSubscriber;

public class Topic
{
    
    private String topicName;
    private String topicId;
    private List<Vechile> vechiles;
    private List<TopicSubscriber> subscribers;

    public Topic( String topicName, String topicId,  List<Vechile> vechiles, List<TopicSubscriber> subscribers )
    {
        super();
        this.topicName = topicName;
        this.topicId = topicId;
        this.vechiles = vechiles;
        this.subscribers = subscribers;
    }
    
    
    public Topic( String topicName, String topicId )
    {
        super();
        this.topicName = topicName;
        this.topicId = topicId;
        this.vechiles = new ArrayList<Vechile>();
        this.subscribers = new ArrayList<>();
    }



    public synchronized void addVechile(final Vechile vechile) {
        
       this.vechiles.add( vechile );
    }

    public void addSubscriber(final TopicSubscriber subscriber) {
        subscribers.add(subscriber);
    }
 
    public String getTopicName()
    {
        return topicName;
    }

    public void setTopicName( String topicName )
    {
        this.topicName = topicName;
    }

    public String getTopicId()
    {
        return topicId;
    }

    public void setTopicId( String topicId )
    {
        this.topicId = topicId;
    }

    public List<TopicSubscriber> getSubscribers()
    {
        return subscribers;
    }

    public void setSubscribers( List<TopicSubscriber> subscribers )
    {
        this.subscribers = subscribers;
    }


    public List<Vechile> getVechiles()
    {
        return vechiles;
    }


    public void setVechiles( List<Vechile> vechiles )
    {
        this.vechiles = vechiles;
    }

    
    
    
    
}
