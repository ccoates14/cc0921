package main.java;

public class RentalDayCount {
    private static final String ILLEGAL_ARGUMENT_MESSAGE = "Rental Day Count must be >= 1, rental day count was: ";
    private final int rentalDayCount;

    public RentalDayCount(final int rentalDayCount) {
        this.rentalDayCount = rentalDayCount;
        throwIllegalArgumentOnIvalidRentDayCount();
    }

    private void throwIllegalArgumentOnIvalidRentDayCount() {
        if (rentalDayCount < 1) throw new IllegalArgumentException(getIllegalArgumentMessage(rentalDayCount));
    }

    public static String getIllegalArgumentMessage(final int rentalDayCount) {
        return ILLEGAL_ARGUMENT_MESSAGE + rentalDayCount;
    }

    public int getRentalDayCount() {
        return rentalDayCount;
    }

    @Override
    public boolean equals(Object dayCount) {
        if (dayCount instanceof RentalDayCount) {
            return ((RentalDayCount) dayCount).getRentalDayCount() == getRentalDayCount();
        }

        return false;
    }
}
