package rentalstore;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {
    private Customer customer;
    private List<Rental> rentals;

    public Receipt(){

    }

    public Receipt(Customer customer, List<Rental> rentals) {
        this.customer = customer;
        this.rentals = rentals;
    }

    private double getItemSubTotalPrice(Rental rental){
        double subTotalPrice = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                subTotalPrice += 2;
                if (rental.getDayRented() > 2) {
                    subTotalPrice += (rental.getDayRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                subTotalPrice += rental.getDayRented() * 3;
                break;
            case Movie.CHILDREN:
                subTotalPrice += 1.5;
                if (rental.getDayRented() > 3) {
                    subTotalPrice += (rental.getDayRented() - 3) * 1.5;
                }
                break;
        }
        return subTotalPrice;
    }

    public String getHeaderStr(){
        return "<H1>Rentals for <EM>" + this.customer.getName() + "</EM></H1><P>\n";
    }

    public String getItemStr(Rental rental){
        return rental.getMovie().getTitle() + ": " + this.getItemSubTotalPrice(rental) + "<BR>\n";
    }

    public String getItemsStr(){
        StringBuilder itemsStrBuilder = new StringBuilder();
        this.rentals.stream().forEach(rental -> itemsStrBuilder.append(getItemStr(rental)));
        return itemsStrBuilder.toString();
    }

    public String getReceiptStr(){
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + this.customer.getName() + "\n";
        List<Rental> rentals = this.rentals.stream().distinct().collect(Collectors.toList());
        Iterator<Rental> rentalSetIterator = rentals.iterator();
        while (rentalSetIterator.hasNext()) {
            Rental each = rentalSetIterator.next();
            double subTotalPrice = getItemSubTotalPrice(each);
            //add frequent renter points
            frequentRenterPoints++;
            //add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDayRented() > 1) {
                frequentRenterPoints++;
            }

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(subTotalPrice) + "\n";
            totalAmount += subTotalPrice;
        }
        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
