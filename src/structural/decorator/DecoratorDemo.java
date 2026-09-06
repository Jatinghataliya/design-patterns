package structural.decorator;

import structural.decorator.beverage.Beverage;
import structural.decorator.beverage.DarkRoast;
import structural.decorator.beverage.Espresso;
import structural.decorator.decorators.Caramel;
import structural.decorator.decorators.Milk;
import structural.decorator.decorators.Mocha;
import structural.decorator.decorators.WhippedCream;

/**
 * Demo runner for Decorator Pattern: Coffee Customization.
 */
public class DecoratorDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("          DECORATOR DESIGN PATTERN DEMO           ");
        System.out.println("         Coffee Beverage Customization            ");
        System.out.println("==================================================\n");

        // 1. Simple Espresso with no condiments
        System.out.println("--- 1. Simple Espresso ---");
        Beverage espresso = new Espresso();
        printBeverage(espresso);

        // 2. Dark Roast with Milk and Mocha (Caffè Mocha)
        System.out.println("\n--- 2. Dark Roast + Milk + Mocha ---");
        Beverage mochaLatte = new DarkRoast();
        mochaLatte = new Milk(mochaLatte);
        mochaLatte = new Mocha(mochaLatte);
        printBeverage(mochaLatte);

        // 3. Caramel Macchiato: Espresso + Double Milk + Caramel + Whipped Cream
        System.out.println("\n--- 3. Deluxe Caramel Macchiato with Double Milk & Whipped Cream ---");
        Beverage deluxeCoffee = new Espresso();
        deluxeCoffee = new Milk(deluxeCoffee);        // First shot of Milk
        deluxeCoffee = new Milk(deluxeCoffee);        // Double Milk
        deluxeCoffee = new Mocha(deluxeCoffee);       // Mocha
        deluxeCoffee = new Caramel(deluxeCoffee);     // Caramel drizzle
        deluxeCoffee = new WhippedCream(deluxeCoffee);// Whipped cream topping
        printBeverage(deluxeCoffee);

        // 4. Ultra Customization in a single chained expression
        System.out.println("\n--- 4. Chained Construction (like Java I/O Streams) ---");
        Beverage fullyLoadedDarkRoast = new WhippedCream(
                new Caramel(
                        new Mocha(
                                new Milk(
                                        new DarkRoast()
                                )
                        )
                )
        );
        printBeverage(fullyLoadedDarkRoast);
    }

    private static void printBeverage(Beverage beverage) {
        System.out.printf("  Order: %s%n", beverage.getDescription());
        System.out.printf("  Total Cost: $%.2f%n", beverage.getCost());
    }
}
