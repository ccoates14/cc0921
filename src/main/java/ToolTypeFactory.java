package main.java;

import java.util.HashMap;
import java.util.Map;

public class ToolTypeFactory {
    private static Map<String, ToolType> types;

    public static ToolType getToolTypeByName(String name) {
        if (types == null) initTypesMap();

        return types.get(name);
    }

    private static void initTypesMap() {
        types = new HashMap<>(){{
            float dailyCharge = 1.99f;
            boolean weekdayCharge = true;
            boolean weekendCharge = true;
            boolean holidayCharge = false;
            String typeName = "Ladder";
            put(typeName, new ToolType(
                    dailyCharge,
                    weekdayCharge,
                    weekendCharge,
                    holidayCharge,
                    typeName
            ));

            dailyCharge = 1.49f;
            weekdayCharge = true;
            weekendCharge = false;
            holidayCharge = true;
            typeName = "Chainsaw";
            put(typeName, new ToolType(
                    dailyCharge,
                    weekdayCharge,
                    weekendCharge,
                    holidayCharge,
                    typeName
            ));

            dailyCharge = 2.99f;
            weekdayCharge = true;
            weekendCharge = false;
            holidayCharge = false;
            typeName = "Jackhammer";
            put(typeName, new ToolType(
                    dailyCharge,
                    weekdayCharge,
                    weekendCharge,
                    holidayCharge,
                    typeName
            ));

        }};
    }
}
