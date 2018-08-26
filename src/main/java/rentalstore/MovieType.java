package rentalstore;

public enum MovieType {
    REGULAR(2, 2, 1.5),
    NEW_RELEASE(3, 1, 3),
    CHILDREN(1.5, 3, 1.5),
    ART(6, 1, 6);

    private double initialPrice;
    private int initialRentedDays;
    private double extraPrice;

    MovieType(double initialPrice, int initialRentedDays, double extraPrice){
        this.initialPrice = initialPrice;
        this.initialRentedDays = initialRentedDays;
        this.extraPrice = extraPrice;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public int getInitialRentedDays() {
        return initialRentedDays;
    }

    public double getExtraPrice() {
        return extraPrice;
    }
}
