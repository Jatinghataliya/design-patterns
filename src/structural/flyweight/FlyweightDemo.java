package structural.flyweight;

import structural.flyweight.client.Forest;

/**
 * Demo runner for the Flyweight Pattern: Forest Renderer.
 *
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │                        FLYWEIGHT PATTERN                                │
 * │                                                                         │
 * │  Intent: Use sharing to efficiently support a large number of           │
 * │  fine-grained objects. Separate INTRINSIC state (shared, stored in      │
 * │  flyweight) from EXTRINSIC state (unique per context, passed in).       │
 * │                                                                         │
 * │  Participants:                                                           │
 * │   • TreeType          – Flyweight interface                             │
 * │   • ForestTreeType    – Concrete Flyweight (intrinsic: name/color/tex)  │
 * │   • TreeTypeFactory   – Flyweight Factory (cache / pool)                │
 * │   • Tree              – Context (extrinsic: x, y, age + ref to fw)     │
 * │   • Forest            – Client (plants trees, drives rendering)         │
 * └─────────────────────────────────────────────────────────────────────────┘
 *
 * Scenario
 * ────────
 * A game renders a forest with thousands of trees.  Each species type
 * (Oak, Pine, Cherry Blossom, Maple, Willow) has a name, color and a heavy
 * texture asset.  Without Flyweight every Tree object would duplicate those
 * fields.  With Flyweight only ONE ForestTreeType object exists per unique
 * species combination regardless of how many individual trees use it.
 */
public class FlyweightDemo {

    public static void main(String[] args) {

        printHeader();

        // ── 1. Define available species (intrinsic data) ─────────────────────
        // Each row: { name, color, texture }
        String[][] species = {
            { "Oak",            "Dark Green",  "oak_bark_texture.png"    },
            { "Pine",           "Forest Green","pine_needle_texture.png"  },
            { "Cherry Blossom", "Pink",        "cherry_petal_texture.png" },
            { "Maple",          "Orange-Red",  "maple_leaf_texture.png"   },
            { "Willow",         "Light Green", "willow_bark_texture.png"  },
        };

        Forest forest = new Forest();

        // ── 2. Manually plant a handful of trees (shows cache hits clearly) ──
        System.out.println("\n──────────────────────────────────────────────────");
        System.out.println("  PHASE 1 – Planting first batch of trees manually");
        System.out.println("──────────────────────────────────────────────────");

        forest.plantTree(10,  20, 35, "Oak",   "Dark Green",  "oak_bark_texture.png");
        forest.plantTree(55,  80, 12, "Pine",  "Forest Green","pine_needle_texture.png");
        forest.plantTree(120, 45, 7,  "Oak",   "Dark Green",  "oak_bark_texture.png");  // reuse Oak flyweight
        forest.plantTree(200, 10, 50, "Maple", "Orange-Red",  "maple_leaf_texture.png");
        forest.plantTree(75, 175, 22, "Pine",  "Forest Green","pine_needle_texture.png"); // reuse Pine flyweight
        forest.plantTree(300, 90, 90, "Oak",   "Dark Green",  "oak_bark_texture.png");  // reuse Oak flyweight again

        System.out.printf("%n  Trees planted: %d  |  Flyweights in pool: %d%n",
                forest.getTreeCount(), forest.getFlyweightCount());

        // ── 3. Render the first batch ─────────────────────────────────────────
        System.out.println("\n──────────────────────────────────────────────────");
        System.out.println("  PHASE 2 – Rendering first batch");
        System.out.println("──────────────────────────────────────────────────");
        forest.draw("SceneCanvas_A");

        // ── 4. Bulk-plant a large random forest ──────────────────────────────
        System.out.println("\n──────────────────────────────────────────────────");
        System.out.println("  PHASE 3 – Planting 1 000 additional random trees");
        System.out.println("──────────────────────────────────────────────────");
        forest.plantRandom(1_000, 1920, 1080, species);

        System.out.printf("%n  Trees planted: %d  |  Flyweights in pool: %d%n",
                forest.getTreeCount(), forest.getFlyweightCount());

        // ── 5. Memory savings summary ─────────────────────────────────────────
        printMemorySummary(forest.getTreeCount(), forest.getFlyweightCount());
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private static void printHeader() {
        System.out.println("==================================================");
        System.out.println("         FLYWEIGHT DESIGN PATTERN DEMO            ");
        System.out.println("            Forest Rendering Engine               ");
        System.out.println("==================================================");
    }

    private static void printMemorySummary(int treeCount, int flyweightCount) {
        // Rough illustration – not a precise JVM measurement.
        // Intrinsic data per flyweight  ≈ 300 bytes (name + color + texture string + object header)
        // Extrinsic data per Tree       ≈ 24 bytes  (3 ints + 1 reference)
        final int intrinsicBytes  = 300;
        final int extrinsicBytes  = 24;

        long withoutFlyweight = (long) treeCount * (intrinsicBytes + extrinsicBytes);
        long withFlyweight    = (long) flyweightCount * intrinsicBytes
                              + (long) treeCount      * extrinsicBytes;
        long saved            = withoutFlyweight - withFlyweight;
        double pct            = (saved * 100.0) / withoutFlyweight;

        System.out.println("\n==================================================");
        System.out.println("  MEMORY SAVINGS SUMMARY (approximate)");
        System.out.println("==================================================");
        System.out.printf("  Total trees (context objects) : %,d%n",  treeCount);
        System.out.printf("  Distinct flyweights in pool   : %d%n",   flyweightCount);
        System.out.printf("  Memory WITHOUT Flyweight      : %,d bytes (~%.1f KB)%n",
                withoutFlyweight, withoutFlyweight / 1024.0);
        System.out.printf("  Memory WITH    Flyweight      : %,d bytes (~%.1f KB)%n",
                withFlyweight,    withFlyweight    / 1024.0);
        System.out.printf("  Estimated savings             : %,d bytes (~%.1f KB) — %.1f%%%n",
                saved, saved / 1024.0, pct);
        System.out.println("==================================================");
    }
}
