package structural.decorator.decorators;

import structural.decorator.beverage.Beverage;

/**
 * Concrete Decorator: Milk.
 */
public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Steamed Milk";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.50;
    }
}
