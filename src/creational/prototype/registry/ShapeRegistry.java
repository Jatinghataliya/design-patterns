package creational.prototype.registry;

import creational.prototype.shapes.Circle;
import creational.prototype.shapes.Rectangle;
import creational.prototype.shapes.Shape;

import java.util.HashMap;
import java.util.Map;

/**
 * Prototype Registry / Cache.
 * Stores a set of pre-configured prototype objects ready to be cloned on demand.
 */
public class ShapeRegistry {
    private final Map<String, Shape> cache = new HashMap<>();

    public ShapeRegistry() {
        loadPreconfiguredPrototypes();
    }

    private void loadPreconfiguredPrototypes() {
        Circle redCircle = new Circle();
        redCircle.setX(10);
        redCircle.setY(10);
        redCircle.setColor("Red");
        redCircle.setRadius(25);
        cache.put("Red Circle", redCircle);

        Rectangle blueRectangle = new Rectangle();
        blueRectangle.setX(0);
        blueRectangle.setY(0);
        blueRectangle.setColor("Blue");
        blueRectangle.setWidth(100);
        blueRectangle.setHeight(50);
        cache.put("Blue Rectangle", blueRectangle);
    }

    public void put(String key, Shape shape) {
        cache.put(key, shape);
    }

    /**
     * Returns a CLONE of the prototype cached under 'key'.
     */
    public Shape get(String key) {
        Shape prototype = cache.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("No prototype found for key: " + key);
        }
        return prototype.clone();
    }
}
