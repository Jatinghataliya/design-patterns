package creational.abstract_factory.app;

import creational.abstract_factory.factories.GUIFactory;
import creational.abstract_factory.products.Button;
import creational.abstract_factory.products.Checkbox;

/**
 * Client Application.
 * Knows only the abstract GUIFactory and abstract product interfaces.
 * Never refers to Windows* or Mac* classes directly.
 */
public class Application {
    private final Button button;
    private final Checkbox checkbox;
    private final String platformName;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
        this.platformName = factory.getPlatformName();
    }

    public void renderUI() {
        System.out.println("Rendering UI for platform: " + platformName);
        button.render();
        checkbox.render();
    }

    public void simulateUserInteraction() {
        System.out.println("Simulating user actions on " + platformName + "...");
        button.onClick();
        checkbox.toggle();
    }
}
