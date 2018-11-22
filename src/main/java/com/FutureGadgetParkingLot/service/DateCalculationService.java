package com.FutureGadgetParkingLot.service;

import com.FutureGadgetParkingLot.domain.Ticket;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
public class DateCalculationService {
    public long calculateDuration(Ticket ticket) {
        LocalDateTime timeIn = localDateFromTimestamp(ticket.getTimeIn());
        LocalDateTime timeOut = localDateFromTimestamp(ticket.getTimeOut());
        Duration duration = Duration.between(timeIn, timeOut);

        return (duration.toMinutes());
    }

    public static LocalDateTime localDateFromTimestamp(Timestamp timestamp) {
        return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneOffset.ofHours(0));
    }
}
