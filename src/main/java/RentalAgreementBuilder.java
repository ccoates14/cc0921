package main.java;

import java.time.LocalDate;

public class RentalAgreementBuilder {
    private Tool tool;
    private RentalDayCount dayCount;
    private LocalDate checkoutDate;
    private Discount discount;

    public RentalAgreementBuilder withTool(final Tool tool) {
        this.tool = tool;
        return this;
    }

    public RentalAgreementBuilder withRentalDayCount(final RentalDayCount count) {
        this.dayCount = count;
        return this;
    }

    public RentalAgreementBuilder withDiscount(final Discount discount) {
        this.discount = discount;
        return this;
    }

    public RentalAgreementBuilder withCheckoutDate(final LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    public RentalAgreement build() {
        return new RentalAgreement(tool, dayCount, checkoutDate, discount);
    }
}
