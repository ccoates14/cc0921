package main.java;

import java.util.*;

public class ToolCode extends ValidCode{
    private static Set<String> codes = new HashSet<>();

    static {
        codes.addAll(
                Arrays.asList(
                "LADW",
                "CHNS",
                "JAKR",
                "JAKD"
                ));
    }

    public ToolCode(String code) {
        super(code, codes);
    }

    public String getCode() {
        return code;
    }

    public static Set<String> getCodes() {
        return codes;
    }

    @Override
    public boolean equals(Object code) {
        if (code instanceof ToolCode)
            return ((ToolCode)code).getCode().equals(getCode());
        return false;
    }
}
