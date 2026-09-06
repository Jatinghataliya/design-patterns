package creational.prototype;

import creational.prototype.registry.ShapeRegistry;
import creational.prototype.shapes.Circle;
import creational.prototype.shapes.Rectangle;
import creational.prototype.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo runner for Prototype Pattern.
 */
public class PrototypeDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("         PROTOTYPE DESIGN PATTERN DEMO            ");
        System.out.println("==================================================\n");

        // 1. Direct Cloning (Polymorphic cloning without knowing concrete class)
        System.out.println("--- 1. Polymorphic Direct Cloning ---");
        List<Shape> originalShapes = new ArrayList<>();

        Circle circle = new Circle();
        circle.setX(10);
        circle.setY(20);
        circle.setRadius(15);
        circle.setColor("Green");
        originalShapes.add(circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(30);
        rectangle.setY(40);
        rectangle.setWidth(200);
        rectangle.setHeight(100);
        rectangle.setColor("Purple");
        originalShapes.add(rectangle);

        // Clone all shapes in the list polymorphically
        List<Shape> clonedShapes = new ArrayList<>();
        for (Shape s : originalShapes) {
            clonedShapes.add(s.clone());
        }

        System.out.println("Original Shapes:");
        for (Shape s : originalShapes) {
            System.out.println("  " + s);
        }

        System.out.println("\nCloned Shapes:");
        for (Shape s : clonedShapes) {
            System.out.println("  " + s);
        }

        // Validate that cloned objects are separate instances with equal values
        System.out.println("\n--- 2. Instance Identity vs Value Equality Check ---");
        for (int i = 0; i < originalShapes.size(); i++) {
            Shape orig = originalShapes.get(i);
            Shape clone = clonedShapes.get(i);

            System.out.println("Shape #" + (i + 1) + ":");
            System.out.println("  - orig == clone ? " + (orig == clone) + " (Distinct objects in memory)");
            System.out.println("  - orig.equals(clone) ? " + orig.equals(clone) + " (Identical property values)");
        }

        // 3. Using Prototype Registry (Cache)
        System.out.println("\n--- 3. Fetching and Customizing Clones from Registry ---");
        ShapeRegistry registry = new ShapeRegistry();

        Shape clonedRedCircle1 = registry.get("Red Circle");
        Shape clonedRedCircle2 = registry.get("Red Circle");
        clonedRedCircle2.setColor("Bright Crimson"); // modify clone without mutating original prototype

        System.out.println("Clone 1 from Registry: " + clonedRedCircle1);
        System.out.println("Clone 2 from Registry (modified color): " + clonedRedCircle2);
        System.out.println("Clone 1 == Clone 2 ? " + (clonedRedCircle1 == clonedRedCircle2) + " (Each fetch is a fresh clone)");
    }
}
