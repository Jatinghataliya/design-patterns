package structural.decorator.beverage;

/**
 * Concrete Component 1: Espresso.
 * Base coffee beverage.
 */
public class Espresso implements Beverage {

    @Override
    public String getDescription() {
        return "Espresso";
    }

    @Override
    public double getCost() {
        return 1.99;
    }
}
