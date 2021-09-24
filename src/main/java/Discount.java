package main.java;

public class Discount {
    private static final String ILLEGAL_ARGUMENT_MESSAGE = "Discount must be >= 0 and <= 100, the discount was: ";
    final int discount;

    public Discount(final int discount) {
        this.discount = discount;
        throwIllegalArgumentOnInvalidDiscount();
    }

    public int getDiscount() {
        return discount;
    }

    private void throwIllegalArgumentOnInvalidDiscount() {
        if (discount < 0 || discount > 100) throw new IllegalArgumentException(getIllegalArgumentMessage(discount));
    }

    public static String getIllegalArgumentMessage(final int discount) {
        return ILLEGAL_ARGUMENT_MESSAGE + discount;
    }

    @Override
    public boolean equals(Object discount) {
        if (discount instanceof Discount) {
            return ((Discount)discount).getDiscount() == getDiscount();
        }

        return false;
    }
}
