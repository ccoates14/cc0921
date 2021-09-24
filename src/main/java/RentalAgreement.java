package main.java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.text.DecimalFormat;


public class RentalAgreement {
    private final Tool tool;
    private final RentalDayCount rentalDayCount;
    private final LocalDate checkoutDate;
    private final Discount discount;

    public RentalAgreement(Tool tool, RentalDayCount rentalDayCount, LocalDate checkoutDate, Discount discount) {
        this.tool = tool;
        this.rentalDayCount = rentalDayCount;
        this.checkoutDate = checkoutDate;
        this.discount = discount;
    }

    public String getPrintableRentalAgreement() {
        String printableAgreement = "";

        printableAgreement += "Tool code: " + getToolCode().getCode() + "\n";
        printableAgreement += "Tool type: " + getToolType().getName() + "\n";
        printableAgreement += "Tool brand: " + getBrand().getBrand() + "\n";
        printableAgreement += "Rental days: " + getRentalDayCount().getRentalDayCount() + "\n";
        printableAgreement += "Check out date: " + getCheckoutDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + "\n";
        printableAgreement += "Due date: " + getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")) + "\n";
        printableAgreement += "Daily rental charge: " + DecimalFormat.getCurrencyInstance(Locale.US).format(getDailyRentalCharge()) + "\n";
        printableAgreement += "Charge days: " + getChargeDays() + "\n";
        printableAgreement += "Pre-discount charge: " + DecimalFormat.getCurrencyInstance(Locale.US).format(getPrediscountCharge()) + "\n";
        printableAgreement += "Discount percent: " + getDiscount().getDiscount() + "%\n";
        printableAgreement += "Discount amount: " + DecimalFormat.getCurrencyInstance(Locale.US).format(getDiscountAmount()) + "\n";
        printableAgreement += "Final charge: " + DecimalFormat.getCurrencyInstance(Locale.US).format(getFinalChargeAmount()) + "\n";

        return printableAgreement;
    }

    public void printRentalAgreement() {
        System.out.println(getPrintableRentalAgreement());
    }

    public ToolCode getToolCode() {
        return getTool().getToolCode();
    }

    public Tool getTool() {
        return tool;
    }

    public ToolType getToolType() {
        return getTool().getToolType();
    }

    public RentalDayCount getRentalDayCount() {
        return rentalDayCount;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public Discount getDiscount() {
        return discount;
    }

    public float getPrediscountCharge() {
        return Math.round(getDailyRentalCharge() * getChargeDays() * 100f) / 100.0f;
    }

    public float getFinalChargeAmount() {
        return Math.round((getPrediscountCharge() - getDiscountAmount()) * 100f) / 100f;
    }

    public float getDiscountAmount() {
        return Math.round(getPrediscountCharge() * (getDiscount().getDiscount()/100f) * 100f) / 100f;
    }

    public Brand getBrand() {
        return getTool().getBrandName();
    }

    public LocalDate getDueDate() {
        return getCheckoutDate().plusDays(getRentalDayCount().getRentalDayCount());
    }

    public float getDailyRentalCharge() {
        return getToolType().getDailyCharge();
    }

    public long getChargeDays() {
        long chargeDays;
        LocalDate dueDate = getDueDate();
        chargeDays = getCheckoutDate().datesUntil(getDueDate()).filter(d -> {
            final DayOfWeek dayOfWeek = d.getDayOfWeek();
            final boolean isWeekend = dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
            final boolean isWeekday = !isWeekend;
            final boolean isHoliday = d.getMonth().equals(Month.SEPTEMBER) && dayOfWeek.equals(DayOfWeek.MONDAY) ||
                    d.getMonth().equals(Month.JULY) && d.getDayOfMonth() == 4;

            return ((isWeekend && getToolType().isWeekendCharge()) ||
                    (isWeekday && getToolType().isWeekdayCharge()) ||
                    (isHoliday && getToolType().isHolidayCharge())
            );
        }).count();

        //account for the possibility that the fourth of july happened either the day after the due date or the day
        //before the due date
        if (dueDate.getMonth().equals(Month.JULY)) {
            final int DAY_AFTER_INDEPENDENCE_DAY = 5;
            final int DAY_BEFORE_INDEPENDENCE_DAY = 3;

            if (dueDate.getDayOfWeek().equals(DayOfWeek.MONDAY) && dueDate.getDayOfMonth() == DAY_AFTER_INDEPENDENCE_DAY) {
                if (getToolType().isHolidayCharge() && !getToolType().isWeekendCharge()) chargeDays++;
            } else if (dueDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) && dueDate.getDayOfMonth() == DAY_BEFORE_INDEPENDENCE_DAY) {
                if (getToolType().isHolidayCharge() && !getToolType().isWeekdayCharge()) chargeDays++;
            }
        }

        return chargeDays - 1;
    }

}
