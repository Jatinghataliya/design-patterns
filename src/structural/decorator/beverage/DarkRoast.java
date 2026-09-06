package structural.decorator.beverage;

/**
 * Concrete Component 2: Dark Roast.
 * Base coffee beverage.
 */
public class DarkRoast implements Beverage {

    @Override
    public String getDescription() {
        return "Dark Roast Coffee";
    }

    @Override
    public double getCost() {
        return 2.49;
    }
}
