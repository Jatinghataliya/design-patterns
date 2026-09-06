package creational.abstract_factory;

import creational.abstract_factory.app.Application;
import creational.abstract_factory.factories.GUIFactory;
import creational.abstract_factory.factories.MacFactory;
import creational.abstract_factory.factories.WindowsFactory;

/**
 * Demo runner for Abstract Factory Pattern.
 */
public class AbstractFactoryDemo {

    /**
     * Application configuration helper that picks the factory based on environment or OS.
     */
    private static Application configureApplication(String osName) {
        GUIFactory factory;

        if (osName.toLowerCase().contains("mac") || osName.toLowerCase().contains("osx")) {
            factory = new MacFactory();
        } else {
            factory = new WindowsFactory();
        }

        return new Application(factory);
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       ABSTRACT FACTORY DESIGN PATTERN DEMO       ");
        System.out.println("==================================================\n");

        // 1. Simulating Windows environment
        System.out.println("--- Scenario 1: Running on Windows OS ---");
        Application winApp = configureApplication("Windows 11");
        winApp.renderUI();
        winApp.simulateUserInteraction();

        System.out.println("\n--------------------------------------------------");

        // 2. Simulating macOS environment
        System.out.println("\n--- Scenario 2: Running on macOS ---");
        Application macApp = configureApplication("macOS Sonoma");
        macApp.renderUI();
        macApp.simulateUserInteraction();
    }
}
