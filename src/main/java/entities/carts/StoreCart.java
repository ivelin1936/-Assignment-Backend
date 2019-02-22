package entities.carts;

import exceptions.InvalidItemsCollectionException;
import interfaces.Cart;
import interfaces.Item;

import java.util.ArrayList;
import java.util.List;

public class StoreCart implements Cart {

    private static final String INVALID_ITEMS_LIST = "Item's list is invalid.";

    private List<Item> items;

    public StoreCart(List<Item> items) {
        this.setItems(items);
    }

    public StoreCart() {
        this.items = new ArrayList<>();
    }

    private void setItems(List<Item> items) {
        if (items == null || items.size() == 0) {
            throw new InvalidItemsCollectionException(INVALID_ITEMS_LIST);
        }
        this.items = items;
    }

    @Override
    public void addItem(Item item) {
        this.items.add(item);
    }

    @Override
    public double getPurchaseValue() {
        return this.items.stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }
}
