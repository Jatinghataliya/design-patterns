package structural.decorator.beverage;

/**
 * Component Interface.
 * Declares common methods for all coffees and decorator condiments.
 */
public interface Beverage {
    String getDescription();
    double getCost();
}
