package creational.prototype.shapes;

import java.util.Objects;

/**
 * Concrete Prototype: Circle
 */
public class Circle extends Shape {
    private int radius;

    public Circle() {
    }

    /**
     * Copy constructor calling super(source).
     */
    public Circle(Circle source) {
        super(source);
        if (source != null) {
            this.radius = source.radius;
        }
    }

    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (!(o instanceof Circle)) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }

    @Override
    public String toString() {
        return "Circle [x=" + getX() + ", y=" + getY() + ", color=" + getColor() + ", radius=" + radius + "]";
    }
}
