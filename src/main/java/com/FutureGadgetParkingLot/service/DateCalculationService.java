package com.FutureGadgetParkingLot.service;

import com.FutureGadgetParkingLot.domain.Ticket;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class DateCalculationService {
    public long calculateDuration(final Ticket ticket) {
        checkTimeOut(ticket);
        if (!ticket.getLost()) {
            LocalDateTime timeIn = localDateFromTimestamp(ticket.getTimeIn());
            LocalDateTime timeOut = localDateFromTimestamp(ticket.getTimeOut());
            Duration duration = Duration.between(timeIn, timeOut);
            return (duration.toMinutes());
        } else {
            return 0;
        }

    }

    public static LocalDateTime localDateFromTimestamp(final Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public void checkTimeOut(final Ticket ticket) {
        if (ticket.getTimeOut() == null) {
            ticket.setLost(true);
        }
    }
}
