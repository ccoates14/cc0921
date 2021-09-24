package main.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for managing the types of tools available for rent.
 */
public class ToolFactory {

    private static Map<String, Tool> codeToTool;

    public static Tool getToolByCode(final String code) {
        if (codeToTool == null) {
            initCodeToToolMap();
        }

        return codeToTool.get(code);
    }

    private static void initCodeToToolMap() {
        codeToTool = new HashMap<>() {{
            ToolType type = ToolTypeFactory.getToolTypeByName("Ladder");
            Brand brand = new Brand("Werner");
            ToolCode code = new ToolCode("LADW");
            put(code.getCode(), new Tool(
                    type,
                    brand,
                    code
            ));

            type = ToolTypeFactory.getToolTypeByName("Chainsaw");
            brand = new Brand("Stihl");
            code = new ToolCode("CHNS");
            put(code.getCode(), new Tool(
                    type,
                    brand,
                    code
            ));

            type = ToolTypeFactory.getToolTypeByName("Jackhammer");
            brand = new Brand("Ridgid");
            code = new ToolCode("JAKR");
            put(code.getCode(), new Tool(
                    type,
                    brand,
                    code
            ));

            brand = new Brand("DeWalt");
            code = new ToolCode("JAKD");
            put(code.getCode(), new Tool(
                    type,
                    brand,
                    code
            ));
        }};
    }
}
