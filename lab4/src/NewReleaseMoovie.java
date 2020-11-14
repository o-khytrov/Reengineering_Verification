public class NewReleaseMoovie extends Movie{

    public static final int FREQUENT_RENTER_POINTS = 1;
    public static final int BASE_RENTAL_COST = 3;

    public NewReleaseMoovie(String title) {
        super(title);
    }

    public double getCharge(int daysRented) {
        double aRental = 0;
        aRental += daysRented * BASE_RENTAL_COST;
        return aRental;
    }

    public int  getFrequentRenterPoints(int daysRented) {
        // add frequent renter points
        int frequentRenterPoints = FREQUENT_RENTER_POINTS;

        // add bonus for a two day new release rental
        if ( daysRented > 1) frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
