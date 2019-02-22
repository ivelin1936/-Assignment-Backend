package interfaces;

import entities.items.ItemImpl;
import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    private static final String DEFAULT_ITEM_NAME = "TV";
    private static final String EMPTY_STRING = "";
    private static final double DEFAULT_ITEM_PRICE = 101D;
    private static final double NEGATIVE_PRICE = -101D;
    private static final double ASSERT_EQUALS_DELTA = 0.001D;

    private Item item;

    @Test(expected = IllegalArgumentException.class)
    public void testNameValidationWithNull() {
        this.item = new ItemImpl(null, DEFAULT_ITEM_PRICE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameValidationWithEmptyStr() {
        this.item = new ItemImpl(EMPTY_STRING, DEFAULT_ITEM_PRICE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPriceValidation() {
        this.item = new ItemImpl(DEFAULT_ITEM_NAME, NEGATIVE_PRICE);
    }

    @Test
    public void testGetNameMethod() {
        this.item = new ItemImpl(DEFAULT_ITEM_NAME, DEFAULT_ITEM_PRICE);
        String actual = this.item.getName();
        Assert.assertEquals(DEFAULT_ITEM_NAME, actual);
    }

    @Test
    public void testGetPriceMethod() {
        this.item = new ItemImpl(DEFAULT_ITEM_NAME, DEFAULT_ITEM_PRICE);
        double actual = this.item.getPrice();
        Assert.assertEquals(DEFAULT_ITEM_PRICE, actual, ASSERT_EQUALS_DELTA);
    }
}