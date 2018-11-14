import com.FutureGadgetParkingLot.FutureGadgetParkingLot.domain.Pricing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingTest {
    private Pricing pricing;

    @Before public void initialize() {
        pricing = new Pricing(1, 1, 10, "M", 30);
    }

    @Test
    public void shouldGetCorrectDuration() {
        assertEquals(10, pricing.getDuration());
    }

    @Test
    public void shouldGetCorrectPrice() {
        assertEquals(30, pricing.getPrice());
    }

    @Test
    public void shouldSortByDuration () {
        ArrayList<Pricing> p1 = new ArrayList<>();
        ArrayList<Pricing> p2 = new ArrayList<>();
        Pricing pricing2 = new Pricing(2, 1, 20, "M", 50);

        p1.add(pricing);
        p1.add(pricing2);

        p2.add(pricing2);
        p2.add(pricing);

        p2.sort(Pricing.sortByDuration);

        assertEquals(p1, p2);
    }
}
