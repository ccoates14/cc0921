package main.java.test;

import main.java.Tool;
import main.java.ToolFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToolFactoryTest {

    @Test
    /**
     * Test we can get a tool returned based off of a valid tool code and no exception was thrown
     */
    public void sanityCheck() {
        ToolFactory.getToolByCode("LADW");
    }

    @Test
    /**
     * Test we can retrieve correct tool for code LADW
     */
    public void getLADW_Tool() {
        final String toolCode = "LADW";
        final String brand = "Werner";
        final String type = "Ladder";
        final Tool tool = ToolFactory.getToolByCode(toolCode);

        //assert it has the above code with, Werner brand and is of type ladder
        Assertions.assertEquals(toolCode, tool.getToolCode().getCode(), "Code should equal " + toolCode);
        Assertions.assertEquals(brand, tool.getBrandName().getBrand(), "Brand should equal " + brand);
        Assertions.assertEquals(type, tool.getToolType().getName(), "Tool Type should equal: " + type);
    }

    @Test
    /**
     * Test we can retrieve correct tool for code CHNS
     */
    public void getCHNS_Tool(){
        final String toolCode = "CHNS";
        final String brand = "Stihl";
        final String type = "Chainsaw";
        final Tool tool = ToolFactory.getToolByCode(toolCode);

        //assert it has the above code with, Werner brand and is of type ladder
        Assertions.assertEquals(toolCode, tool.getToolCode().getCode(), "Code should equal " + toolCode);
        Assertions.assertEquals(brand, tool.getBrandName().getBrand(), "Brand should equal " + brand);
        Assertions.assertEquals(type, tool.getToolType().getName(), "Tool Type should equal: " + type);
    }

    @Test
    /**
     * Test we can retrieve correct tool for code JAKR
     */
    public void getJAKR_Tool(){
        final String toolCode = "JAKR";
        final String brand = "Ridgid";
        final String type = "Jackhammer";
        final Tool tool = ToolFactory.getToolByCode(toolCode);

        //assert it has the above code with, Werner brand and is of type ladder
        Assertions.assertEquals(toolCode, tool.getToolCode().getCode(), "Code should equal " + toolCode);
        Assertions.assertEquals(brand, tool.getBrandName().getBrand(), "Brand should equal " + brand);
        Assertions.assertEquals(type, tool.getToolType().getName(), "Tool Type should equal: " + type);
    }

    @Test
    /**
     * Test we can retrieve correct tool for code JAKD
     */
    public void getJAKD_Tool(){
        final String toolCode = "JAKD";
        final String brand = "DeWalt";
        final String type = "Jackhammer";
        final Tool tool = ToolFactory.getToolByCode(toolCode);

        //assert it has the above code with, Werner brand and is of type ladder
        Assertions.assertEquals(toolCode, tool.getToolCode().getCode(), "Code should equal " + toolCode);
        Assertions.assertEquals(brand, tool.getBrandName().getBrand(), "Brand should equal " + brand);
        Assertions.assertEquals(type, tool.getToolType().getName(), "Tool Type should equal: " + type);
    }
}