public class RegularMovie extends  Movie{

    public static final int FREQUENT_RENTER_POINTS = 1;
    public static final int BASE_RENTAL_COST = 2;
    public static final int NUM_DAYS_BASE_CONST = 2;
    public static final double LONG_TERM_RENTAL_COST = 1.5;

    public RegularMovie(String title) {
        super(title);
    }

    public double getCharge(int daysRented) {
        double aRental = 0;

        aRental += BASE_RENTAL_COST;
        if (daysRented > NUM_DAYS_BASE_CONST)
            aRental += (daysRented - NUM_DAYS_BASE_CONST) * LONG_TERM_RENTAL_COST;

        return aRental;
    }

    public int  getFrequentRenterPoints(int daysRented) {
        // add frequent renter points
        int frequentRenterPoints = FREQUENT_RENTER_POINTS;
        return frequentRenterPoints;
    }
}
