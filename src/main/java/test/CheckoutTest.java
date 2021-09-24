package main.java.test;

import main.java.Checkout;
import main.java.CheckoutBuilder;
import main.java.Discount;
import main.java.RentalDayCount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

class CheckoutTest {

    @Test
    /**
     * Test that we can pass in valid data and no exception will
     * be thrown.
     */
    public void sanityCheck() {
        final String toolCode = "toolcode"; //this will eventually be generated or grabbed from our factory
        final int rentDayCount = 10;
        final int discountPercent = 20;
        final LocalDate date = LocalDate.now();

        new Checkout(toolCode, rentDayCount, discountPercent, date);
    }

    @Test
    /**
     * Test Checkout will throw illegalArgumentException when an invalid rental day count is passed.
     * Rental day count must at least be 1
     */
    public void illegalArgumentExceptionIsThrownOnBadRentalDayCount() {
        final int illegalRentalDayCount = -1;
        final CheckoutBuilder checkoutBuilder = getDefaultValidCheckoutArgs().withRentalDayCount(illegalRentalDayCount);
        final String expectedIllegalRentDayCountMessage = RentalDayCount.getIllegalArgumentMessage(checkoutBuilder.getRentDayCount());

        final Exception e = assertThrows(IllegalArgumentException.class ,() -> {
            checkoutBuilder.build();
        });

        assertEquals(expectedIllegalRentDayCountMessage, e.getMessage());
    }

    @Test
    /**
     * Test Checkout will throw illegalArgumentException when discount is invalid.
     * Discount must be >= 0 and <= 100
     */
    public void illegalArgumentExceptionIsThrownOnBadDiscount() {
        final int illegalDiscount = -1;
        final CheckoutBuilder checkoutBuilder = getDefaultValidCheckoutArgs().withDiscountPercent(illegalDiscount);
        final String expectedIllegalDiscountMessage = Discount.getIllegalArgumentMessage(checkoutBuilder.getDiscountPercent());

        final Exception e = assertThrows(IllegalArgumentException.class, () -> {
           checkoutBuilder.build();
        });

        assertEquals(expectedIllegalDiscountMessage, e.getMessage());
    }

    private CheckoutBuilder getDefaultValidCheckoutArgs() {
        final String toolCode = "toolcode"; //this will get generated from a factory later
        final int rentalDayCount = 10;
        final int discountPercent = 20;
        final LocalDate checkoutDate = LocalDate.now();

        return new CheckoutBuilder().withToolCode(toolCode).withRentalDayCount(rentalDayCount).withDiscountPercent(discountPercent).withCheckoutDate(checkoutDate);
    }

}