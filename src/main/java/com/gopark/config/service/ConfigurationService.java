package com.gopark.config.service;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopark.models.ParkingConfig;

public class ConfigurationService
{
    private ParkingConfig parkingConfig;
    
    private static ConfigurationService configService;
    
    private ConfigurationService () {
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream( "sport_event.json" );
            JsonNode jsonNode = mapper.readValue( in, JsonNode.class );
            parkingConfig  = mapper.readValue( mapper.writeValueAsString( jsonNode ) , ParkingConfig.class );
        } catch ( IOException e ) {
        }
    }
    
    public static ConfigurationService getInstance() {
        if (configService == null) {
            configService = new ConfigurationService();
        }
        return configService;
    }

    public ParkingConfig getParkingConfig()
    {
        return parkingConfig;
    }
}
