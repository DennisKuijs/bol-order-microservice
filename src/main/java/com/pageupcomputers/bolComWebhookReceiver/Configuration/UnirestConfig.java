package com.pageupcomputers.bolComWebhookReceiver.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import kong.unirest.jackson.JacksonObjectMapper;
import kong.unirest.Unirest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class UnirestConfig {
    
    /**
     * Inject Jackson Object Mapper
     */
    @Autowired
    private ObjectMapper mapper;


    /**
     * Configure Unirest to use the Jackson Object Mapper instead of the default one.
     */
    @PostConstruct
    public void postConstruct() {
        Unirest.config().setObjectMapper(new JacksonObjectMapper() {
            
            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}