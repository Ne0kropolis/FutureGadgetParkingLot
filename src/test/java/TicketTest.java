import com.FutureGadgetParkingLot.domain.Pricing;
import com.FutureGadgetParkingLot.domain.PricingScheme;
import com.FutureGadgetParkingLot.domain.Ticket;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TicketTest {
    private Ticket blankTicket;
    private Ticket ticketWithPrice;
    private Ticket ticketWithoutPrice;

    @Before
    public void initialize() {
        blankTicket = new Ticket();
        ticketWithPrice = new Ticket(1, 100, "2018-11-09", "10:15", "11:20", 30, false);
        ticketWithoutPrice = new Ticket(1, 100, "2018-11-09", "10:15", "11:20", false);
    }

    @Test
    public void shouldCreateTicketWithParameters() {
        assertEquals(ticketWithPrice, ticketWithPrice);
    }

    @Test
    public void shouldEnsureTicketWithoutPriceEqualsZero() {
        assertNotEquals(ticketWithoutPrice,ticketWithPrice);
    }

    @Test
    public void shouldReturnCorrectPrice() {
        assertEquals(30, ticketWithPrice.getPrice());
    }

    @Test
    public void shouldCalculateCorrectPrice() {
        List<Pricing> prices = new ArrayList<>();
        prices.add(new Pricing(1, 1, 10, "M", 10));
        prices.add(new Pricing(2 , 1, 40, "M", 30));
        PricingScheme pricingScheme = new PricingScheme(prices);
        int duration = 31;

        ticketWithoutPrice.calculatePrice(duration, pricingScheme);

        assertEquals(30, ticketWithoutPrice.getPrice());
    }
}
