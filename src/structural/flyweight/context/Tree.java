package structural.flyweight.context;

import structural.flyweight.flyweight.TreeType;

/**
 * Context: Tree
 *
 * Stores the EXTRINSIC (unique-per-instance) state:
 *   - x, y  : position on the canvas
 *   - age   : age of this individual tree in years
 *
 * Holds a REFERENCE to a shared {@link TreeType} flyweight instead of
 * duplicating the heavy intrinsic data (name, color, texture) for every tree.
 *
 * Memory profile (before Flyweight):  each Tree stores ALL data  → O(n * full_size)
 * Memory profile (after  Flyweight):  each Tree stores 3 ints + 1 pointer → O(n * tiny)
 *                                     flyweight pool stores M unique types  → O(M * full_size)
 */
public class Tree {

    // ── Extrinsic state (unique per tree instance) ────────────────────────────
    private final int x;
    private final int y;
    private final int age;

    // ── Reference to shared flyweight (intrinsic state lives here) ───────────
    private final TreeType treeType;

    public Tree(int x, int y, int age, TreeType treeType) {
        this.x        = x;
        this.y        = y;
        this.age      = age;
        this.treeType = treeType;
    }

    /**
     * Delegates rendering to the flyweight, passing extrinsic state as args.
     *
     * @param canvas target canvas / scene name
     */
    public void draw(String canvas) {
        treeType.render(canvas, x, y, age);
    }

    // ── Accessors ─────────────────────────────────────────────────────────────
    public int getX()             { return x; }
    public int getY()             { return y; }
    public int getAge()           { return age; }
    public TreeType getTreeType() { return treeType; }
}
