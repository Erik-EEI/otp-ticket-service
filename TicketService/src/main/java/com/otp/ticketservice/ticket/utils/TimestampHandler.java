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
        String dateString = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (Long.parseLong(timestampStr)*1000));
        LocalDateTime eventDate = LocalDateTime.parse(dateString);
        LocalDateTime currentDate = LocalDateTime.now();

        if(currentDate.isAfter(eventDate)) throw new RuntimeException(); //TODO Replace with custom exception

    }
}
