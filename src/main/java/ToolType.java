package main.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ToolType extends ValidCode{
    private static final Set<String> toolTypeNames = new HashSet<>();

    static {
        toolTypeNames.addAll(
                Arrays.asList(
                        "Ladder",
                        "Chainsaw",
                        "Jackhammer"
                )
        );
    }

    private final float dailyCharge;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    public ToolType(final float dailyCharge, final boolean weekdayCharge, final boolean weekendCharge,
                    final boolean holidayCharge, final String typeName) {
        super(typeName, toolTypeNames);
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;

    }

    public float getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public String getName() {
        return code;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    public static Set<String> getToolTypeNames() {
        return toolTypeNames;
    }

    @Override
    public boolean equals(Object toolType) {
        if (toolType instanceof ToolType) {
            ToolType toolTypeCasted = (ToolType) toolType;

            return toolTypeCasted.isHolidayCharge() == isHolidayCharge() &&
                    toolTypeCasted.isWeekdayCharge() == isWeekdayCharge() &&
                    toolTypeCasted.isWeekendCharge() == isWeekendCharge() &&
                    toolTypeCasted.getName().equals(getName()) &&
                    toolTypeCasted.getDailyCharge() == getDailyCharge() ;
        }

        return false;
    }

    @Override
    public String toString() {
        return "ToolType{" +
                "dailyCharge=" + dailyCharge +
                ", weekdayCharge=" + weekdayCharge +
                ", weekendCharge=" + weekendCharge +
                ", holidayCharge=" + holidayCharge +
                '}';
    }

}
