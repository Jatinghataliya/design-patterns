package structural.flyweight.flyweight;

/**
 * Flyweight Interface
 *
 * Declares the operation that accepts the extrinsic state (x, y, age) passed
 * in by the client at runtime.  All intrinsic (shared) state lives in the
 * concrete flyweight implementation.
 */
public interface TreeType {

    /**
     * Renders a tree on the canvas.
     *
     * @param canvas  name of the target canvas / scene
     * @param x       x-coordinate of this particular tree (extrinsic)
     * @param y       y-coordinate of this particular tree (extrinsic)
     * @param age     age of this particular tree in years  (extrinsic)
     */
    void render(String canvas, int x, int y, int age);
}
