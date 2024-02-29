package com.otp.ticketservice.ticket.utils;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@UtilityClass
public class TimestampHandler {
    public static void checkStartTime(String timestampStr){
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date (Long.parseLong(timestampStr)*1000));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime eventDate = LocalDateTime.parse(dateString,formatter);
        LocalDateTime currentDate = LocalDateTime.now();

        if(currentDate.isAfter(eventDate)) throw new RuntimeException(""); //TODO Replace with custom exception
    }
}
