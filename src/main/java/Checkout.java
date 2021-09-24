package main.java;

import java.time.LocalDate;

public class Checkout {
    private final String toolCode;
    private final RentalDayCount rentDayCount;
    private final Discount discountPercent;
    private final LocalDate checkoutDate;

    public Checkout(final String toolCode, final int rentDayCount, final int discountPercent, final LocalDate checkoutDate) {
        this.toolCode = toolCode;
        this.rentDayCount = new RentalDayCount(rentDayCount);
        this.discountPercent = new Discount(discountPercent);
        this.checkoutDate = checkoutDate;
    }

    public RentalAgreement getRentalAgreement() {
        return null;
    }
}
