package main.java.test;

import main.java.ToolType;
import main.java.ToolTypeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToolTypeFactoryTest {

    @Test
    /**
     * Just a sanity check to make sure we can simply
     * retrieve a tooltype given a valid type
     */
    public void sanityCheck() {
        ToolTypeFactory.getToolTypeByName("Ladder");
    }

    @Test
    public void getLadder() {
        final String type = "Ladder";
        final float dailyCharge = 1.99f;
        final boolean weekdayCharge = true;
        final boolean weekendCharge = true;
        final boolean holidayCharge = false;

        final ToolType toolType = ToolTypeFactory.getToolTypeByName(type);

        Assertions.assertEquals(type, toolType.getName(), "Types should equal with type: " + type);
        Assertions.assertEquals(dailyCharge, toolType.getDailyCharge(), "Daily charges should equal with: " + dailyCharge);
        Assertions.assertEquals(weekdayCharge, toolType.isWeekdayCharge(), "Weekday charge should equal with: " + weekdayCharge);
        Assertions.assertEquals(weekendCharge, toolType.isWeekendCharge(), "Weekend charge should equal with: " + weekendCharge);
        Assertions.assertEquals(holidayCharge, toolType.isHolidayCharge(), "Holiday charge should equal with: " + holidayCharge);
    }

    @Test
    public void getChainsaw() {
        final String type = "Chainsaw";
        final float dailyCharge = 1.49f;
        final boolean weekdayCharge = true;
        final boolean weekendCharge = false;
        final boolean holidayCharge = true;

        final ToolType toolType = ToolTypeFactory.getToolTypeByName(type);

        Assertions.assertEquals(type, toolType.getName(), "Types should equal with type: " + type);
        Assertions.assertEquals(dailyCharge, toolType.getDailyCharge(), "Daily charges should equal with: " + dailyCharge);
        Assertions.assertEquals(weekdayCharge, toolType.isWeekdayCharge(), "Weekday charge should equal with: " + weekdayCharge);
        Assertions.assertEquals(weekendCharge, toolType.isWeekendCharge(), "Weekend charge should equal with: " + weekendCharge);
        Assertions.assertEquals(holidayCharge, toolType.isHolidayCharge(), "Holiday charge should equal with: " + holidayCharge);
    }

    @Test
    public void getJackhammer() {
        final String type = "Jackhammer";
        final float dailyCharge = 2.99f;
        final boolean weekdayCharge = true;
        final boolean weekendCharge = false;
        final boolean holidayCharge = false;

        final ToolType toolType = ToolTypeFactory.getToolTypeByName(type);

        Assertions.assertEquals(type, toolType.getName(), "Types should equal with type: " + type);
        Assertions.assertEquals(dailyCharge, toolType.getDailyCharge(), "Daily charges should equal with: " + dailyCharge);
        Assertions.assertEquals(weekdayCharge, toolType.isWeekdayCharge(), "Weekday charge should equal with: " + weekdayCharge);
        Assertions.assertEquals(weekendCharge, toolType.isWeekendCharge(), "Weekend charge should equal with: " + weekendCharge);
        Assertions.assertEquals(holidayCharge, toolType.isHolidayCharge(), "Holiday charge should equal with: " + holidayCharge);
    }

}