package rentalstore;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReceiptTest {

    @Test
    public void should_output_receipt_with_total_price_2_when_rent_REGULAR_movie_for_one_day() {
        Customer customer = new Customer("Dylan");
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("Titanic", Movie.REGULAR), 1)
        );
        Receipt receipt = new Receipt(customer, rentals);
        String expectedReceipt =
                "Rental Record for Dylan\n" +
                        "\tTitanic\t2.0\n" +
                        "Amount owed is 2.0\n" +
                        "You earned 1 frequent renter points";
        assertThat(receipt.getReceiptStr(), is(expectedReceipt));
    }

    @Test
    public void should_output_receipt_with_total_price_6_point_5_when_rent_NEW_RELEASE_movie_1_day_and_REGULAR_for_3_days() {
        Customer customer = new Customer("Dylan");
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("Titanic", Movie.REGULAR), 3),
                new Rental(new Movie("The Ring", Movie.NEW_RELEASE), 1)
        );
        Receipt receipt = new Receipt(customer, rentals);
        String expectedReceipt =
                "Rental Record for Dylan\n" +
                        "\tTitanic\t3.5\n" +
                        "\tThe Ring\t3.0\n" +
                        "Amount owed is 6.5\n" +
                        "You earned 2 frequent renter points";
        assertThat(receipt.getReceiptStr(), is(expectedReceipt));
    }

    @Test
    public void should_return_receipt_with_total_price_8_when_rent_REGULAR_3_days_and_NEW_RELEASE_1_day_and_CHILDRENS_3_days() {
        Customer customer = new Customer("Dylan");
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("Titanic", Movie.REGULAR), 3),
                new Rental(new Movie("The Ring", Movie.NEW_RELEASE), 1),
                new Rental(new Movie("One Day", Movie.CHILDREN), 3)
        );
        Receipt receipt = new Receipt(customer, rentals);
        String expectedReceipt =
                "Rental Record for Dylan\n" +
                        "\tTitanic\t3.5\n" +
                        "\tThe Ring\t3.0\n" +
                        "\tOne Day\t1.5\n" +
                        "Amount owed is 8.0\n" +
                        "You earned 3 frequent renter points";
        assertThat(receipt.getReceiptStr(), is(expectedReceipt));
    }

    @Test
    public void should_return_correct_items_details() {
        Customer customer = new Customer("Dylan");
        List<Rental> rentals = Arrays.asList(
                new Rental(new Movie("Titanic", Movie.REGULAR), 3),
                new Rental(new Movie("The Ring", Movie.NEW_RELEASE), 1),
                new Rental(new Movie("One Day", Movie.CHILDREN), 3)
        );
        Receipt receipt = new Receipt(customer, rentals);
        String expectedItemsStr =
                "Titanic: 3.5<BR>\n" +
                        "The Ring: 3.0<BR>\n" +
                        "One Day: 1.5<BR>\n";
        assertThat(receipt.getItemsHtml(), is(expectedItemsStr));
    }

}