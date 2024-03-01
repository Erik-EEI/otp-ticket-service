package com.otp.ticketservice.ticket.dao;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.ticket.utils.HttpRequestUtil;
import com.otp.ticketservice.ticket.utils.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EventDAO {

    private final UrlBuilder urlBuilder;

    @Autowired
    public EventDAO(UrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    public HttpResponse<String> getAllEvents(){
        String url = urlBuilder.buildUrl("getEvents");

        return HttpRequestUtil.getRequest(url);
    }

    public HttpResponse<String> getEvent(EventDataDTO eventData){
        Map<String,String> params = new HashMap<>();
        params.put("eventId",eventData.eventId().toString());

        String url = urlBuilder.buildUrl("getEvent",params);

        return HttpRequestUtil.getRequest(url);
    }

    public HttpResponse<String> getDetailedEvent(EventDataDTO eventData){
        Map<String,String> params = new HashMap<>();
        params.put("eventId",eventData.eventId().toString());
        params.put("detailed","true");

        String url = urlBuilder.buildUrl("getEvent",params);

        return HttpRequestUtil.getRequest(url);

    }

}
