package main.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Brand extends ValidCode{
    private static final Set<String> brands = new HashSet<>();

    static {
        brands.addAll(
                Arrays.asList(
                        "Werner",
                        "Stihl",
                        "Ridgid",
                        "DeWalt"
                )
        );
    }

    public Brand(String brand) {
        super(brand, brands);
    }

    public String getBrand() {
        return code;
    }

    public static Set<String> getBrands() {
        return brands;
    }

    @Override
    public boolean equals(Object brand) {
        if (brand instanceof Brand) {
            return ((Brand)brand).getBrand().equals(getBrand());
        }

        return false;
    }
}
