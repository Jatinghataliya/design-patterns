package structural.flyweight.client;

import structural.flyweight.context.Tree;
import structural.flyweight.factory.TreeTypeFactory;
import structural.flyweight.flyweight.TreeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Client: Forest
 *
 * Owns the collection of {@link Tree} context objects and uses
 * {@link TreeTypeFactory} to obtain (or reuse) flyweights.
 *
 * The Forest can hold hundreds of thousands of Tree instances cheaply because
 * each Tree only stores three ints plus a pointer to a shared flyweight.
 */
public class Forest {

    private final List<Tree>       trees   = new ArrayList<>();
    private final TreeTypeFactory  factory = new TreeTypeFactory();
    private final Random           random  = new Random(42);   // fixed seed → reproducible demo

    // ── Public API ────────────────────────────────────────────────────────────

    /**
     * Adds a new tree at (x, y) with given age and species data.
     * The factory ensures the flyweight is reused if the (name,color,texture)
     * combination already exists in the pool.
     */
    public void plantTree(int x, int y, int age,
                          String name, String color, String texture) {
        TreeType type = factory.getTreeType(name, color, texture);
        trees.add(new Tree(x, y, age, type));
    }

    /**
     * Convenience method: randomly plants {@code count} trees chosen from
     * {@code species} specifications within a canvas of {@code width x height}.
     *
     * @param count    number of trees to plant
     * @param width    canvas width  (pixels / units)
     * @param height   canvas height (pixels / units)
     * @param species  array of { name, color, texture } triples
     */
    public void plantRandom(int count, int width, int height, String[][] species) {
        for (int i = 0; i < count; i++) {
            String[] s  = species[random.nextInt(species.length)];
            int x   = random.nextInt(width);
            int y   = random.nextInt(height);
            int age = 1 + random.nextInt(200);
            plantTree(x, y, age, s[0], s[1], s[2]);
        }
    }

    /**
     * Renders all trees on the given canvas.
     *
     * @param canvas target canvas name
     */
    public void draw(String canvas) {
        System.out.printf("%n  Rendering %d trees on canvas \"%s\"...%n", trees.size(), canvas);
        for (Tree tree : trees) {
            tree.draw(canvas);
        }
    }

    // ── Diagnostics ───────────────────────────────────────────────────────────

    /** Total number of Tree context objects planted in this forest. */
    public int getTreeCount() {
        return trees.size();
    }

    /** Number of distinct flyweight objects created (== unique species combos). */
    public int getFlyweightCount() {
        return factory.getCacheSize();
    }
}
