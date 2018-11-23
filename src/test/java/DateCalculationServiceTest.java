import com.FutureGadgetParkingLot.domain.Ticket;
import com.FutureGadgetParkingLot.service.DateCalculationService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DateCalculationServiceTest {
    private Ticket ticket;
    DateCalculationService dateCalculationService;

    @Before
    public void initialize() {
        dateCalculationService = new DateCalculationService();
        ticket = new Ticket(1,100, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 30, false);
    }

    @Test
    public void shouldReturnTimeIn() {
        assertEquals(32, this.dateCalculationService.calculateDuration(ticket));
    }

    @Test
    public void shouldConvertTimestampToLocalDateTime() {
        String time = "2018-08-08T20:08:08";
        LocalDateTime expectedLocalDateTime = LocalDateTime.parse(time);
        LocalDateTime actualLocalDateTime = dateCalculationService.localDateFromTimestamp(ticket.getTimeIn());


        assertEquals(expectedLocalDateTime, actualLocalDateTime);
    }

    @Test
    public void shouldSetLostTicketOnEmptyTimeOut() {
        Ticket ticketWithoutTimeOut = new Ticket(1,100, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), false);
        dateCalculationService.checkTimeOut(ticketWithoutTimeOut);

        assertEquals(true, ticketWithoutTimeOut.getLost());
    }
}
