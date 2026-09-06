package structural.flyweight.flyweight;

/**
 * Concrete Flyweight: ForestTreeType
 *
 * Stores the INTRINSIC (shared, immutable) state that is identical across
 * potentially thousands of Tree instances:
 *   - name    : species name  (e.g. "Oak", "Pine", "Cherry Blossom")
 *   - color   : leaf/bark color
 *   - texture : path / descriptor of the heavy texture asset
 *
 * This object is created ONCE per unique (name, color, texture) combination
 * and reused by every Tree that shares those properties.
 */
public class ForestTreeType implements TreeType {

    // ── Intrinsic state (heavy, shared, immutable) ────────────────────────────
    private final String name;
    private final String color;
    private final String texture;   // simulates a loaded texture resource

    public ForestTreeType(String name, String color, String texture) {
        this.name    = name;
        this.color   = color;
        this.texture = texture;
        // In a real system this constructor would load the texture from disk,
        // which is exactly the expensive step we want to do only once.
        System.out.printf("  [ForestTreeType] CREATED new flyweight  ► name=%-16s color=%-10s texture=%s%n",
                name, color, texture);
    }

    // ── Flyweight operation ───────────────────────────────────────────────────

    /**
     * Renders the tree by combining intrinsic state (this object) with the
     * extrinsic state (canvas, x, y, age) supplied by the caller.
     */
    @Override
    public void render(String canvas, int x, int y, int age) {
        System.out.printf("  [Render] %-16s | color=%-10s | texture=%-20s | canvas=%-12s | pos=(%4d,%4d) | age=%d yrs%n",
                name, color, texture, canvas, x, y, age);
    }

    // ── Accessors (used by factory for cache-key checks) ─────────────────────
    public String getName()    { return name;    }
    public String getColor()   { return color;   }
    public String getTexture() { return texture; }

    @Override
    public String toString() {
        return String.format("ForestTreeType{name='%s', color='%s', texture='%s'}", name, color, texture);
    }
}
