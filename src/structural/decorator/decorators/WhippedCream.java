package structural.decorator.decorators;

import structural.decorator.beverage.Beverage;

/**
 * Concrete Decorator: Whipped Cream.
 */
public class WhippedCream extends CondimentDecorator {

    public WhippedCream(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whipped Cream";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.45;
    }
}
