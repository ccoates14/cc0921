package main.java;

import java.time.LocalDate;

public class CheckoutBuilder {

    private String toolCode;
    private int rentDayCount;
    private int discountPercent;
    private LocalDate checkoutDate;

    public CheckoutBuilder withToolCode(String toolCode) {
        this.toolCode = toolCode;
        return this;
    }

    public CheckoutBuilder withRentalDayCount(int rentalDayCount) {
        this.rentDayCount = rentalDayCount;
        return this;
    }

    public CheckoutBuilder withDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
        return this;
    }

    public CheckoutBuilder withCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    public Checkout build() {
        return new Checkout(toolCode, rentDayCount, discountPercent, checkoutDate);
    }

    public String getToolCode() {
        return toolCode;
    }

    public int getRentDayCount() {
        return rentDayCount;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }
}
