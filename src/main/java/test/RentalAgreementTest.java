package main.java.test;

import main.java.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class RentalAgreementTest {


    //sanity test
    @Test
    public void sanityTest() {
        getDefaultRentalAgreement();
    }

    //get tool code
    @Test
    public void testGetCode() {
        final ToolCode code = new ToolCode("JAKD");
        final RentalAgreement agreement = getDefaultRentalAgreement().withTool(ToolFactory.
                getToolByCode(code.getCode())).build();

        assertEquals(code, agreement.getToolCode());
    }

    //get tool
    @Test
    public void testGetTool() {
        final ToolCode code = new ToolCode("LADW");
        final Tool tool = ToolFactory.getToolByCode(code.getCode());
        final RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).build();

        assertEquals(tool, agreement.getTool());
    }

    //get rental day count
    @Test
    public void testGetDayCount() {
        final RentalDayCount dayCount = new RentalDayCount(10);
        final RentalAgreement agreement = getDefaultRentalAgreement().withRentalDayCount(dayCount).build();

        assertEquals(dayCount, agreement.getRentalDayCount());
    }

    //get checkout date
    @Test
    public void testGetCheckoutDate() {
        final LocalDate checkoutDate = LocalDate.now();
        final RentalAgreement agreement = getDefaultRentalAgreement().withCheckoutDate(checkoutDate).build();

        assertEquals(checkoutDate, agreement.getCheckoutDate());
    }

    //get discount
    @Test
    public void testGetDiscount() {
        final Discount discount = new Discount(10);
        final RentalAgreement agreement = getDefaultRentalAgreement().withDiscount(discount).build();

        assertEquals(discount, agreement.getDiscount());
    }

    @Test
    public void testGetDiscountAmount() {
        Tool tool = ToolFactory.getToolByCode("LADW");
        LocalDate checkoutDate = LocalDate.now().withDayOfMonth(10).withMonth(9).withYear(2021);
        RentalDayCount dayCount = new RentalDayCount(14);
        RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).withCheckoutDate(checkoutDate)
                .withRentalDayCount(dayCount).build();
        float expectedDiscountAmount = 5.17f;

        assertEquals(expectedDiscountAmount, agreement.getDiscountAmount());
    }

    @Test
    public void testGetPrediscountCharge() {
        Tool tool = ToolFactory.getToolByCode("LADW");
        LocalDate checkoutDate = LocalDate.now().withDayOfMonth(10).withMonth(9).withYear(2021);
        RentalDayCount dayCount = new RentalDayCount(14);
        RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).withCheckoutDate(checkoutDate)
                .withRentalDayCount(dayCount).build();
        int expectedChargeDays = 13;
        float expectedPrediscountCharge = expectedChargeDays * tool.getToolType().getDailyCharge();

        assertEquals(expectedPrediscountCharge, agreement.getPrediscountCharge());
    }

    //get tool type
    @Test
    public void testGetToolType() {
        final ToolCode code = new ToolCode("JAKD");
        final ToolType type = ToolFactory.getToolByCode(code.getCode()).getToolType();
        final RentalAgreement agreement = getDefaultRentalAgreement().
                withTool(ToolFactory.getToolByCode(code.getCode())).build();

        assertEquals(type, agreement.getToolType());
    }

    //get brand
    @Test
    public void testGetBrand() {
        final Brand brand = new Brand("Werner");
        final RentalAgreement agreement = getDefaultRentalAgreement().build();

        assertEquals(brand, agreement.getBrand());
    }

    //get due date
    @Test
    public void testGetDueDate() {
        final LocalDate checkoutDate = LocalDate.now();
        final RentalDayCount dayCount = new RentalDayCount(10);
        final LocalDate expectedDueDate = checkoutDate.plusDays(dayCount.getRentalDayCount());
        final RentalAgreement agreement = getDefaultRentalAgreement().
                withCheckoutDate(checkoutDate).withRentalDayCount(dayCount).build();

        assertEquals(expectedDueDate, agreement.getDueDate());
    }

    //get daily rental charge
    @Test
    public void testGetDailyRentalCharge() {
        final ToolType type = ToolTypeFactory.getToolTypeByName("Ladder");
        final float dailyRentalCharge = type.getDailyCharge();
        final RentalAgreement agreement = getDefaultRentalAgreement().build();

        assertEquals(dailyRentalCharge, agreement.getDailyRentalCharge());
    }

    //get charge days
    @Test
    public void testGetChargeDaysLadderNoHolidays() {
        Tool tool = ToolFactory.getToolByCode("LADW");
        LocalDate checkoutDate = LocalDate.now().withDayOfMonth(10).withMonth(9).withYear(2021);
        RentalDayCount dayCount = new RentalDayCount(14);
        RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).withCheckoutDate(checkoutDate)
                .withRentalDayCount(dayCount).build();
        int expectedChargeDays = 13;

        assertEquals(expectedChargeDays, agreement.getChargeDays());
    }

    @Test
    public void getChargeDaysLadderWithHolidays() {
        final Tool tool = ToolFactory.getToolByCode("LADW");
        final LocalDate checkoutDate = LocalDate.now().withDayOfMonth(3).withMonth(7).withYear(2021);
        final RentalDayCount dayCount = new RentalDayCount(5);
        final RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).withCheckoutDate(checkoutDate)
                .withRentalDayCount(dayCount).build();
        final int expectedChargeDays = 4;

        assertEquals(expectedChargeDays, agreement.getChargeDays());
    }

    @Test
    public void getChargeDaysChainsawNoHoliday() {
        final Tool tool = ToolFactory.getToolByCode("CHNS");
        final LocalDate checkoutDate = LocalDate.now().withDayOfMonth(3).withMonth(5).withYear(2021);
        final RentalDayCount dayCount = new RentalDayCount(5);
        final RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).withCheckoutDate(checkoutDate)
                .withRentalDayCount(dayCount).build();
        final int expectedChargeDays = 4;

        assertEquals(expectedChargeDays, agreement.getChargeDays());
    }

    @Test
    public void getChargeDaysChainsawWithHoliday() {
        final Tool tool = ToolFactory.getToolByCode("CHNS");
        final LocalDate checkoutDate = LocalDate.now().withDayOfMonth(3).withMonth(7).withYear(2021);
        final RentalDayCount dayCount = new RentalDayCount(10);
        final RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).withCheckoutDate(checkoutDate)
                .withRentalDayCount(dayCount).build();
        final int expectedChargeDays = 6;

        assertEquals(expectedChargeDays, agreement.getChargeDays());
    }

    @Test
    public void getChargeDaysJackhammerNoHoliday() {
        final Tool tool = ToolFactory.getToolByCode("JAKD");
        final LocalDate checkoutDate = LocalDate.now().withDayOfMonth(3).withMonth(5).withYear(2021);
        final RentalDayCount dayCount = new RentalDayCount(10);
        final RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).withCheckoutDate(checkoutDate)
                .withRentalDayCount(dayCount).build();
        final int expectedChargeDays = 7;

        assertEquals(expectedChargeDays, agreement.getChargeDays());
    }

    @Test
    public void getChargeDaysJackhammerWithHoliday() {
        final Tool tool = ToolFactory.getToolByCode("JAKD");
        final LocalDate checkoutDate = LocalDate.now().withDayOfMonth(3).withMonth(7).withYear(2021);
        final RentalDayCount dayCount = new RentalDayCount(10);
        final RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).withCheckoutDate(checkoutDate)
                .withRentalDayCount(dayCount).build();
        final int expectedChargeDays = 5;

        assertEquals(expectedChargeDays, agreement.getChargeDays());
    }

    //get final charge
    @Test
    public void getFinalChargeAmount() {
        Tool tool = ToolFactory.getToolByCode("LADW");
        LocalDate checkoutDate = LocalDate.now().withDayOfMonth(10).withMonth(9).withYear(2021);
        RentalDayCount dayCount = new RentalDayCount(14);
        RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).withCheckoutDate(checkoutDate)
                .withRentalDayCount(dayCount).build();
        int expectedChargeDays = 13;
        float expectedPrediscountCharge = expectedChargeDays * tool.getToolType().getDailyCharge();
        float expectedFinalChargeAmount = expectedPrediscountCharge - agreement.getDiscountAmount();

        assertEquals(expectedFinalChargeAmount, agreement.getFinalChargeAmount());
    }

    @Test
    public void testGetPrintableAgreement() {
        Tool tool = ToolFactory.getToolByCode("LADW");
        LocalDate checkoutDate = LocalDate.now().withDayOfMonth(10).withMonth(9).withYear(2021);
        RentalDayCount dayCount = new RentalDayCount(14);
        RentalAgreement agreement = getDefaultRentalAgreement().withTool(tool).withCheckoutDate(checkoutDate)
                .withRentalDayCount(dayCount).build();
        agreement.printRentalAgreement();
    }

    @Test
    public void testScenarioIllegalDiscount() {
        final String args[] = {
          "JAKR",
          "09/03/15",
          "5",
          "101"
        };

        assertThrows(IllegalArgumentException.class ,() -> {
            getRentalAgreementFromStrings(args).build();
        });
    }

    @Test
    public void testScenario2() {
        final String args[] = {
                "LADW",
                "07/02/20",
                "3",
                "10"
        };

        RentalAgreement agreement = getRentalAgreementFromStrings(args).build();

        assertEquals(args[0], agreement.getToolCode().getCode());
        assertEquals(LocalDate.parse(args[1], DateTimeFormatter.ofPattern("MM/dd/yy")), agreement.getCheckoutDate());
        assertEquals(Integer.parseInt(args[2]), agreement.getRentalDayCount().getRentalDayCount());
        assertEquals(Integer.parseInt(args[3]), agreement.getDiscount().getDiscount());

        //number of charge days = 2
        final int expectedNumberOfChargeDays = 2;
        assertEquals(expectedNumberOfChargeDays, agreement.getChargeDays());

        final float dailyCharge = 1.99f;
        final float discount = .1f;
        final float expectedTotalCost = Math.round(((dailyCharge * expectedNumberOfChargeDays) - (dailyCharge * expectedNumberOfChargeDays * discount)) * 100) / 100f;
        assertEquals(expectedTotalCost, agreement.getFinalChargeAmount());
    }

    @Test
    public void testScenario3() {
        final String args[] = {
                "CHNS",
                "07/02/15",
                "5",
                "25"
        };

        RentalAgreement agreement = getRentalAgreementFromStrings(args).build();

        assertEquals(args[0], agreement.getToolCode().getCode());
        assertEquals(LocalDate.parse(args[1], DateTimeFormatter.ofPattern("MM/dd/yy")), agreement.getCheckoutDate());
        assertEquals(Integer.parseInt(args[2]), agreement.getRentalDayCount().getRentalDayCount());
        assertEquals(Integer.parseInt(args[3]), agreement.getDiscount().getDiscount());

        final int expectedNumberOfChargeDays = 3;
        assertEquals(expectedNumberOfChargeDays, agreement.getChargeDays());

        final float dailyCharge = 1.49f;
        final float discount = .25f;
        final float expectedTotalCost = Math.round(((dailyCharge * expectedNumberOfChargeDays) - (dailyCharge * expectedNumberOfChargeDays * discount)) * 100) / 100f;
        assertEquals(expectedTotalCost, agreement.getFinalChargeAmount());
    }

    @Test
    public void testScenario4() {
        final String args[] = {
                "JAKD",
                "09/03/15",
                "6",
                "0"
        };

        RentalAgreement agreement = getRentalAgreementFromStrings(args).build();

        assertEquals(args[0], agreement.getToolCode().getCode());
        assertEquals(LocalDate.parse(args[1], DateTimeFormatter.ofPattern("MM/dd/yy")), agreement.getCheckoutDate());
        assertEquals(Integer.parseInt(args[2]), agreement.getRentalDayCount().getRentalDayCount());
        assertEquals(Integer.parseInt(args[3]), agreement.getDiscount().getDiscount());

        final int expectedNumberOfChargeDays = 3;
        assertEquals(expectedNumberOfChargeDays, agreement.getChargeDays());

        final float dailyCharge = 2.99f;
        final float discount = 0f;
        final float expectedTotalCost = Math.round(((dailyCharge * expectedNumberOfChargeDays) - (dailyCharge * expectedNumberOfChargeDays * discount)) * 100) / 100f;
        assertEquals(expectedTotalCost, agreement.getFinalChargeAmount());
    }

    @Test
    public void testScenario5() {
        final String args[] = {
                "JAKR",
                "07/02/15",
                "9",
                "0"
        };

        RentalAgreement agreement = getRentalAgreementFromStrings(args).build();

        assertEquals(args[0], agreement.getToolCode().getCode());
        assertEquals(LocalDate.parse(args[1], DateTimeFormatter.ofPattern("MM/dd/yy")), agreement.getCheckoutDate());
        assertEquals(Integer.parseInt(args[2]), agreement.getRentalDayCount().getRentalDayCount());
        assertEquals(Integer.parseInt(args[3]), agreement.getDiscount().getDiscount());

        final int expectedNumberOfChargeDays = 6;
        assertEquals(expectedNumberOfChargeDays, agreement.getChargeDays());

        final float dailyCharge = 2.99f;
        final float discount = 0f;
        final float expectedTotalCost = Math.round(((dailyCharge * expectedNumberOfChargeDays) - (dailyCharge * expectedNumberOfChargeDays * discount)) * 100) / 100f;
        assertEquals(expectedTotalCost, agreement.getFinalChargeAmount());
    }

    @Test
    public void testScenario6() {
        final String args[] = {
                "JAKR",
                "07/02/20",
                "4",
                "50"
        };

        RentalAgreement agreement = getRentalAgreementFromStrings(args).build();

        assertEquals(args[0], agreement.getToolCode().getCode());
        assertEquals(LocalDate.parse(args[1], DateTimeFormatter.ofPattern("MM/dd/yy")), agreement.getCheckoutDate());
        assertEquals(Integer.parseInt(args[2]), agreement.getRentalDayCount().getRentalDayCount());
        assertEquals(Integer.parseInt(args[3]), agreement.getDiscount().getDiscount());

        final int expectedNumberOfChargeDays = 1;
        assertEquals(expectedNumberOfChargeDays, agreement.getChargeDays());

        final float dailyCharge = 2.99f;
        final float discount = .5f;
        final float expectedTotalCost = Math.round(((dailyCharge * expectedNumberOfChargeDays) - (Math.round(dailyCharge * expectedNumberOfChargeDays * discount * 100f) / 100f)) * 100f) / 100f;
        assertEquals(expectedTotalCost, agreement.getFinalChargeAmount());
    }

    private RentalAgreementBuilder getDefaultRentalAgreement() {
        final ToolCode code = new ToolCode("LADW");
        final Tool tool = ToolFactory.getToolByCode(code.getCode());
        final RentalDayCount dayCount = new RentalDayCount(10);
        final LocalDate checkoutDate = LocalDate.now();
        final Discount discount = new Discount(20);

        return new RentalAgreementBuilder().withTool(tool).withRentalDayCount(dayCount).
                withCheckoutDate(checkoutDate).withDiscount(discount);
    }

    private RentalAgreementBuilder getRentalAgreementFromStrings(String ... args) {
        final String code = args[0];
        final String checkoutDate = args[1];
        final String rentalDays = args[2];
        final String discount = args[3];

        return  new RentalAgreementBuilder().withTool(ToolFactory.getToolByCode(code)).
                withCheckoutDate(LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("MM/dd/yy")))
                .withRentalDayCount(new RentalDayCount(Integer.parseInt(rentalDays))).
                withDiscount(new Discount(Integer.parseInt(discount)));
    }
}