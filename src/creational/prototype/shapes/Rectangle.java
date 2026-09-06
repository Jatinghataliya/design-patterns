package creational.prototype.shapes;

import java.util.Objects;

/**
 * Concrete Prototype: Rectangle
 */
public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle() {
    }

    /**
     * Copy constructor calling super(source).
     */
    public Rectangle(Rectangle source) {
        super(source);
        if (source != null) {
            this.width = source.width;
            this.height = source.height;
        }
    }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return width == rectangle.width && height == rectangle.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), width, height);
    }

    @Override
    public String toString() {
        return "Rectangle [x=" + getX() + ", y=" + getY() + ", color=" + getColor() + 
               ", width=" + width + ", height=" + height + "]";
    }
}
