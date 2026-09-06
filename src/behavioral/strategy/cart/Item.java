package behavioral.strategy.cart;

import behavioral.strategy.strategies.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Item in the shopping cart.
 */
public class Item {
    private final String name;
    private final double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}
