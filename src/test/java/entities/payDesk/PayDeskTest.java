package entities.payDesk;

import entities.cards.BronzeCard;
import entities.cards.GoldCard;
import entities.cards.SilverCard;
import entities.carts.StoreCart;
import interfaces.Cart;
import interfaces.DiscountCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PayDeskTest {

    private static final double DEFAULT_PURCHASE_VALUE = 100D;
    private static final double DEFAULT_DISCOUNT_RATE = 2D;
    private static final double DEFAULT_PURCHASE_DISCOUNT = 25D;
    private static final double ASSERT_EQUALS_DELTA = 0.001D;
    private static final String BRONZE_CARD_EXPECTED_INVOICE_FROM_EXAMPLES =
            "Purchase value: $150,00\nDiscount rate: 0,0%\nDiscount: $0,00\nTotal: $150,00";
    private static final String SILVER_CARD_EXPECTED_INVOICE_FROM_EXAMPLES =
            "Purchase value: $850,00\nDiscount rate: 3,5%\nDiscount: $29,75\nTotal: $820,25";
    private static final String GOLD_CARD_EXPECTED_INVOICE_FROM_EXAMPLES =
            "Purchase value: $1300,00\nDiscount rate: 10,0%\nDiscount: $130,00\nTotal: $1170,00";

    private Cart purchaseData;

    @Before
    public void setUp() {
        this.purchaseData = Mockito.mock(StoreCart.class);
    }

    @Test
    public void finalizePurchaseWithBronzeCardFromExamples() {
        DiscountCard bronzeCard = new BronzeCard(0D);
        Mockito.when(this.purchaseData.getPurchaseValue()).thenReturn(150D);

        String expected = BRONZE_CARD_EXPECTED_INVOICE_FROM_EXAMPLES
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        String actual = PayDesk.pay(this.purchaseData, bronzeCard);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void finalizePurchaseWithSilverCardFromExamples() {
        DiscountCard silverCard = new SilverCard(600D);
        Mockito.when(this.purchaseData.getPurchaseValue()).thenReturn(850D);

        String expected = SILVER_CARD_EXPECTED_INVOICE_FROM_EXAMPLES
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        String actual = PayDesk.pay(this.purchaseData, silverCard);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void finalizePurchaseWithGoldCardFromExamples() {
        DiscountCard goldCard = new GoldCard(1500D);
        Mockito.when(this.purchaseData.getPurchaseValue()).thenReturn(1300D);

        String expected = GOLD_CARD_EXPECTED_INVOICE_FROM_EXAMPLES
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        String actual = PayDesk.pay(this.purchaseData, goldCard);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateTotalAmountMethod() {
       Class clazz = PayDesk.class;
        try {
            Method calculateTotalAmountMethod =
                    clazz.getDeclaredMethod("calculateTotalAmount", double.class, double.class);
            calculateTotalAmountMethod.setAccessible(true);

            double expectedValue = DEFAULT_PURCHASE_VALUE - DEFAULT_PURCHASE_DISCOUNT;
            double actualValue = (double) calculateTotalAmountMethod
                    .invoke(clazz, DEFAULT_PURCHASE_VALUE, DEFAULT_PURCHASE_DISCOUNT);

            Assert.assertEquals(expectedValue, actualValue, ASSERT_EQUALS_DELTA);

        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCalculatePurchaseDiscountMethod() {
        Class clazz = PayDesk.class;
        try {
            Method calculateTotalAmountMethod =
                    clazz.getDeclaredMethod("calculatePurchaseDiscount", double.class, double.class);
            calculateTotalAmountMethod.setAccessible(true);

            double expectedValue = DEFAULT_PURCHASE_VALUE * DEFAULT_DISCOUNT_RATE / 100D;

            double actualValue = (double) calculateTotalAmountMethod
                    .invoke(clazz, DEFAULT_PURCHASE_VALUE, DEFAULT_DISCOUNT_RATE);

            Assert.assertEquals(expectedValue, actualValue, ASSERT_EQUALS_DELTA);

        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}