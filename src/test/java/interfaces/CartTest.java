package interfaces;

import entities.carts.StoreCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CartTest {

    private static final int DEFAULT_SIZE_TWO = 2;
    private static final double PRICE_ONE_HUNDRED_DOLLARS = 100D;
    private static final double PRICE_FIFTY_FIVE_DOLLARS = 55D;
    private static final double ASSERT_EQUALS_DELTA = 0.001D;

    private Cart cart;

    @Before
    public void setUp() {
        this.cart = new StoreCart();

        Item firstItem = Mockito.mock(Item.class);
        Mockito.when(firstItem.getPrice()).thenReturn(PRICE_ONE_HUNDRED_DOLLARS);
        Item secondItem = Mockito.mock(Item.class);
        Mockito.when(secondItem.getPrice()).thenReturn(PRICE_FIFTY_FIVE_DOLLARS);

        this.cart.addItem(firstItem);
        this.cart.addItem(secondItem);
    }

    @Test
    public void testEmptyConstructor() {
        Cart testCart = new StoreCart();

        try {
            Field itemsField = StoreCart.class.getDeclaredField("items");
            itemsField.setAccessible(true);
            List<Item> items = (List<Item>) itemsField.get(testCart);

            Assert.assertTrue(items != null);

        } catch (NoSuchFieldException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorValidationWithNullParams() {
        Cart testCart = new StoreCart(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorValidationWithEmptyList() {
        Cart testCart = new StoreCart(new ArrayList<>());
    }

    @Test
    public void testAddItemMethod() {
        try {
            Field itemsField = StoreCart.class.getDeclaredField("items");
            itemsField.setAccessible(true);
            List<Item> items = (List<Item>) itemsField.get(this.cart);

            int expected = DEFAULT_SIZE_TWO;
            int actual = items.size();

            Assert.assertEquals(expected, actual);

        } catch (NoSuchFieldException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPurchaseValueMethod() {
        double expected = PRICE_ONE_HUNDRED_DOLLARS + PRICE_FIFTY_FIVE_DOLLARS;
        double actual = this.cart.getPurchaseValue();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void testPurchaseValueMethod() {
        Item mockItem = Mockito.mock(Item.class);
        Mockito.when(mockItem.getPrice()).thenReturn(PRICE_ONE_HUNDRED_DOLLARS);

        this.cart.addItem(mockItem);

        double expected = PRICE_ONE_HUNDRED_DOLLARS * 2 + PRICE_FIFTY_FIVE_DOLLARS;
        double actual = this.cart.getPurchaseValue();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }
}