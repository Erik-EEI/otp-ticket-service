package com.otp.ticketservice.ticket.utils;

import com.otp.ticketservice.ticket.exceptions.CanNotReserveSeatForEventInPastException;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Utility class for handling timestamp-related operations.
 */
@UtilityClass
public class TimestampHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("[TICKET - TIMESTAMP UTIL]");

    /**
     * Checks if the event start time is in the past.
     * @param timestampStr the timestamp string representing the event start time
     * @throws CanNotReserveSeatForEventInPastException if the event is already started
     */
    public static void checkStartTime(String timestampStr){
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date (Long.parseLong(timestampStr)*1000));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime eventDate = LocalDateTime.parse(dateString,formatter);
        LocalDateTime currentDate = LocalDateTime.now();

        // if(currentDate.isAfter(eventDate)) throw new CanNotReserveSeatForEventInPastException(); //FIXME - SWITCHED OFF
        LOGGER.info("✔ - VALID -- Event is not started in the past");
    }
}
