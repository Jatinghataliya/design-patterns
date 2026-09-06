package creational.abstract_factory.factories;

import creational.abstract_factory.products.Button;
import creational.abstract_factory.products.Checkbox;
import creational.abstract_factory.products.MacButton;
import creational.abstract_factory.products.MacCheckbox;

/**
 * Concrete Factory 2: MacOS GUI Factory
 * Produces consistent MacOS UI components.
 */
public class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }

    @Override
    public String getPlatformName() {
        return "macOS";
    }
}
