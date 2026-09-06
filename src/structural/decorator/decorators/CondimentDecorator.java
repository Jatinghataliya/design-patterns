package structural.decorator.decorators;

import structural.decorator.beverage.Beverage;

/**
 * Base Decorator: CondimentDecorator.
 * Implements Beverage and wraps another Beverage instance.
 */
public abstract class CondimentDecorator implements Beverage {
    protected final Beverage beverage;

    public CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public abstract String getDescription();
}
