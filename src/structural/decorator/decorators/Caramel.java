package structural.decorator.decorators;

import structural.decorator.beverage.Beverage;

/**
 * Concrete Decorator: Caramel drizzle.
 */
public class Caramel extends CondimentDecorator {

    public Caramel(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Caramel Drizzle";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.70;
    }
}
