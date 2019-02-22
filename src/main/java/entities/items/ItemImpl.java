package entities.items;

import exceptions.InvalidNameException;
import exceptions.InvalidPriceException;
import interfaces.Item;

public class ItemImpl implements Item {

    private static final String INVALID_NAME_EX_MSG = "Item name cannot be empty.";
    private static final String INVALID_PRICE_EX_MSG = "Item price cannot be negative.";
    private static final double MIN_PRICE_VALUE = 0D;

    private String name;
    private double price;

    public ItemImpl(String name, double price) {
        this.setName(name);
        this.setPrice(price);
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException(INVALID_NAME_EX_MSG);
        }
        this.name = name;
    }

    private void setPrice(double price) {
        if (price < MIN_PRICE_VALUE) {
            throw new InvalidPriceException(INVALID_PRICE_EX_MSG);
        }
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
