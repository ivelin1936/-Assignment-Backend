package entities.payDesk;

import interfaces.Cart;
import interfaces.DiscountCard;

public final class PayDesk {

    private static final double ONE_HUNDRED = 100D;
    private static final String PURCHASE_VALUE_STR = "Purchase value: $%.2f";
    private static final String DISCOUNT_RATE_STR = "Discount rate: %.1f%%";
    private static final String PURCHASE_DISCOUNT_STR = "Discount: $%.2f";
    private static final String TOTAL_AMOUNT_STR = "Total: $%.2f";

    private PayDesk() {
    }

    public static String pay(Cart shoppingCart, DiscountCard card){
        double cardDiscountRate = card.getDiscountRate();
        double purchaseValue = shoppingCart.getPurchaseValue();

        double purchaseDiscount = calculatePurchaseDiscount(purchaseValue, cardDiscountRate);
        double totalAmount = calculateTotalAmount(purchaseValue, purchaseDiscount);

        StringBuilder invoiceBuilder = new StringBuilder();

        invoiceBuilder
                .append(String.format(PURCHASE_VALUE_STR, purchaseValue))
                .append(System.lineSeparator())
                .append(String.format(DISCOUNT_RATE_STR, cardDiscountRate))
                .append(System.lineSeparator())
                .append(String.format(PURCHASE_DISCOUNT_STR, purchaseDiscount))
                .append(System.lineSeparator())
                .append(String.format(TOTAL_AMOUNT_STR, totalAmount));

        return invoiceBuilder.toString();
    }

    private static double calculatePurchaseDiscount(double purchaseValue, double cardDiscountRate) {
        return purchaseValue * cardDiscountRate / ONE_HUNDRED;
    }

    private static double calculateTotalAmount(double purchaseValue, double discount) {
        return purchaseValue - discount;
    }
}
