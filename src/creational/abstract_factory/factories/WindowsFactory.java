package creational.abstract_factory.factories;

import creational.abstract_factory.products.Button;
import creational.abstract_factory.products.Checkbox;
import creational.abstract_factory.products.WindowsButton;
import creational.abstract_factory.products.WindowsCheckbox;

/**
 * Concrete Factory 1: Windows GUI Factory
 * Produces consistent Windows UI components.
 */
public class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }

    @Override
    public String getPlatformName() {
        return "Windows";
    }
}
