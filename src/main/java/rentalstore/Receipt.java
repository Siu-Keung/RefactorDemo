package rentalstore;

import java.util.List;

public class Receipt {
    private Customer customer;
    private List<Rental> rentals;

    public Receipt() { }

    public Receipt(Customer customer, List<Rental> rentals) {
        this.customer = customer;
        this.rentals = rentals;
    }

    private double calculateRegularMoviePrice(int dayRented) {
        double price = MovieType.REGULAR.getInitialPrice();
        if (dayRented > MovieType.REGULAR.getInitialRentedDays())
            price += (dayRented - MovieType.REGULAR.getInitialRentedDays()) * MovieType.REGULAR.getExtraPrice();
        return price;
    }

    private double calculateNewReleaseMoviePrice(int dayRented) {
        return dayRented * MovieType.NEW_RELEASE.getInitialPrice();
    }

    private double calculateChildrenMoviePrice(int dayRented) {
        double price = MovieType.CHILDREN.getInitialPrice();
        if (dayRented > MovieType.CHILDREN.getInitialRentedDays())
            price += (dayRented - MovieType.CHILDREN.getInitialRentedDays()) * MovieType.CHILDREN.getExtraPrice();
        return price;
    }

    private double getItemSubTotalPrice(Rental rental) {
        switch (rental.getMovie().getMovieType()) {
            case REGULAR:
                return calculateRegularMoviePrice(rental.getDayRented());
            case NEW_RELEASE:
                return calculateNewReleaseMoviePrice(rental.getDayRented());
            case CHILDREN:
                return calculateChildrenMoviePrice(rental.getDayRented());
            default:
                return 0;
        }
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Rental rental : rentals) {
            totalPrice += getItemSubTotalPrice(rental);
        }
        return totalPrice;
    }

    public String getHeaderHtml() {
        return "<H1>Rentals for <EM>" + this.customer.getName() + "</EM></H1><P>\n";
    }

    public String getItemHtml(Rental rental) {
        return rental.getMovie().getTitle() + ": " + this.getItemSubTotalPrice(rental) + "<BR>\n";
    }

    public String getItemsHtml() {
        StringBuilder itemsStrBuilder = new StringBuilder();
        this.rentals.stream().forEach(rental -> itemsStrBuilder.append(getItemHtml(rental)));
        return itemsStrBuilder.toString();
    }

    public int getRentalPointsEarned() {
        int rentalPoints = 0;
        for (Rental rental : rentals) {
            rentalPoints++;
            if (rental.getMovie().getMovieType() == MovieType.NEW_RELEASE && rental.getDayRented() > 1)
                rentalPoints++;
        }
        return rentalPoints;
    }

    public String getFooterHtml() {
        StringBuilder footerHtmlBuilder = new StringBuilder();
        footerHtmlBuilder.append("<P>You owe<EM>" + getTotalPrice() + "</EM><P>\n");
        footerHtmlBuilder.append("On this rental you earned <EM>" + getRentalPointsEarned() + "</EM> frequent renter points<P>");
        return footerHtmlBuilder.toString();
    }

    public String getReceiptHtml() {
        StringBuilder receiptHtml = new StringBuilder();
        receiptHtml.append(getHeaderHtml());
        receiptHtml.append(getItemsHtml());
        receiptHtml.append(getFooterHtml());
        return receiptHtml.toString();
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
