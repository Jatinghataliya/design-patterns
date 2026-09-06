package creational.abstract_factory.factories;

import creational.abstract_factory.products.Button;
import creational.abstract_factory.products.Checkbox;

/**
 * Abstract Factory Interface.
 * Declares factory methods for each product family member.
 */
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
    String getPlatformName();
}
