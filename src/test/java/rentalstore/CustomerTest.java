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
    public void should_output_receipt_with_total_price_2_when_rent_REGULAR_movie_for_one_day() {
        Customer customer = new Customer("Dylan");
        customer.addRental(new Rental(new Movie("Titanic", Movie.REGULAR), 1));
        String expectedReceipt =
                "Rental Record for Dylan\n" +
                        "\tTitanic\t2.0\n" +
                        "Amount owed is 2.0\n" +
                        "You earned 1 frequent renter points";
        assertThat(customer.statement(), is(expectedReceipt));
    }

    @Test
    public void should_output_receipt_with_total_price_6_point_5_when_rent_NEW_RELEASE_movie_1_day_and_REGULAR_for_3_days() {
        Customer customer = new Customer("Dylan");
        customer.addRental(new Rental(new Movie("Titanic", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("The Ring", Movie.NEW_RELEASE), 1));
        String expectedReceipt =
                "Rental Record for Dylan\n" +
                        "\tTitanic\t3.5\n" +
                        "\tThe Ring\t3.0\n" +
                        "Amount owed is 6.5\n" +
                        "You earned 2 frequent renter points";
        assertThat(customer.statement(), is(expectedReceipt));
    }

    @Test
    public void should_return_receipt_with_total_price_8_when_rent_REGULAR_3_days_and_NEW_RELEASE_1_day_and_CHILDRENS_3_days() {
        Customer customer = new Customer("Dylan");
        customer.addRental(new Rental(new Movie("Titanic", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("The Ring", Movie.NEW_RELEASE), 1));
        customer.addRental(new Rental(new Movie("One Day", Movie.CHILDRENS), 3));
        String expectedReceipt =
                "Rental Record for Dylan\n" +
                        "\tTitanic\t3.5\n" +
                        "\tThe Ring\t3.0\n" +
                        "\tOne Day\t1.5\n" +
                        "Amount owed is 8.0\n" +
                        "You earned 3 frequent renter points";
        assertThat(customer.statement(), is(expectedReceipt));
    }


}