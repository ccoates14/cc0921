package main.java.test;

import main.java.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidCodeTest {

    @Test
    /**
     * Test will throw exception on invalid code.
     */
    public void throwsIllegalArgumentExceptionOnInvalidCode() {
        final String invalidCode = "An invalid code";

        assertThrows(IllegalArgumentException.class, () -> {
             new Brand(invalidCode);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ToolCode(invalidCode);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ToolType(50f, true, true, true, invalidCode);
        });
    }

    @Test
    /**
     * Does not throw an illegal argument exception when passed a valid band.
     */
    public void doesNotThrowIllegalArgumentExceptionWhenValid() {
        Brand.getBrands().forEach(brand -> {
            Assertions.assertDoesNotThrow(() -> {
                new Brand(brand);
            });
        });

        ToolType.getToolTypeNames().forEach(name -> {
            Assertions.assertDoesNotThrow(() -> {
                new ToolType(50, true, true, true, name);
            });
        });

        ToolCode.getCodes().forEach(code -> {
            Assertions.assertDoesNotThrow(() -> {
                new ToolCode(code);
            });
        });
    }
}