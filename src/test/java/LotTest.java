import com.FutureGadgetParkingLot.domain.Lot;
import org.junit.Before;
import org.junit.Test;

public class LotTest {

        @Before
        public void initialize() {
            Lot l = new Lot();
        }

        @Test
        public void canCreateWithParameters() {
            int lotId = 0, pricingSchemeNumber = 0, lotCapacity = 0;
            String lotName ="";
            String lotAddress = "";

            Lot l = new Lot(lotId, pricingSchemeNumber, lotName, lotAddress, lotCapacity);
        }


}
