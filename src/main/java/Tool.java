package main.java;

public class Tool {
    private final ToolType toolType;
    private final Brand brand;
    private final ToolCode code;


    public Tool(ToolType toolType, Brand brand, ToolCode code) {
        this.toolType = toolType;
        this.brand = brand;
        this.code = code;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public Brand getBrandName() {
        return brand;
    }

    public ToolCode getToolCode() {
        return code;
    }

    @Override
    public boolean equals(Object tool) {
        if (tool instanceof Tool) {
            Tool toolCasted = ((Tool) tool);
            return toolCasted.getToolCode().equals(this.code) &&
                toolCasted.getToolType().equals(this.toolType) &&
                    toolCasted.getBrandName().equals(this.brand);
        }

        return false;
    }
}
