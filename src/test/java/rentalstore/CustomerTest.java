package rentalstore;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Dylan Wei
 * @date 2018-08-24 14:00
 */
public class CustomerTest {

    @Test
    public void should_output_receipt_with_total_price_3() {
        Customer customer = new Customer("Dylan");
        customer.addRental(new Rental(new Movie("Titanic", Movie.NEW_RELEASE), 1));
        String expectedReceipt =
                "Rental Record for Dylan\n" +
                        "\tTitanic\t3.0\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter points";
        assertThat(customer.statement(), is(expectedReceipt));
    }


}