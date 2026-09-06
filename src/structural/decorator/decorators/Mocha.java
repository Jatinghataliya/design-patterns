package structural.decorator.decorators;

import structural.decorator.beverage.Beverage;

/**
 * Concrete Decorator: Mocha (Chocolate syrup).
 */
public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Rich Mocha";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.60;
    }
}
