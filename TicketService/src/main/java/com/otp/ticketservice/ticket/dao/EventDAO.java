package com.otp.ticketservice.ticket.dao;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.ticket.utils.HttpRequestUtil;
import com.otp.ticketservice.ticket.utils.UrlBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EventDAO {
    @Cacheable("events")
    public HttpResponse<String> getAllEvents(){
        String url = UrlBuilder.buildUrl("getEvents");

        return HttpRequestUtil.getRequest(url);
    }

    public HttpResponse<String> getEvent(EventDataDTO eventData){
        Map<String,String> params = new HashMap<>();
        params.put("eventId",eventData.eventId().toString());

        String url = UrlBuilder.buildUrl("getEvent",params);

        return HttpRequestUtil.getRequest(url);
    }

    public HttpResponse<String> getDetailedEvent(EventDataDTO eventData){
        Map<String,String> params = new HashMap<>();
        params.put("eventId",eventData.eventId().toString());
        params.put("detailed","true");

        String url = UrlBuilder.buildUrl("getEvent",params);

        return HttpRequestUtil.getRequest(url);

    }

}
