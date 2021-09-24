package main.java;

import java.util.Set;

public abstract class ValidCode {
    protected String code;

    public ValidCode(String code, Set<String> validCodes) {
        this.code = code;
        throwsIllegalCodeException(validCodes, code);
    }

    public static String getIllegalCodeMessage(Set<String> validCodes, String codeUsed) {
        return "Illegal code must be in: " + validCodes.toString() + " ,code was: " + codeUsed;
    }

    protected void throwsIllegalCodeException(Set<String> validCodes, String code) {
        if (!validCodes.contains(code)) throw new IllegalArgumentException(getIllegalCodeMessage(validCodes, code));
    }
}
