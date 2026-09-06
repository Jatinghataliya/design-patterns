package structural.flyweight.factory;

import structural.flyweight.flyweight.ForestTreeType;
import structural.flyweight.flyweight.TreeType;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Factory: TreeTypeFactory
 *
 * Manages the pool of shared {@link ForestTreeType} flyweights.
 * Returns an existing instance when the requested (name, color, texture)
 * combination is already cached; otherwise creates and caches a new one.
 *
 * Thread-safety note: {@code synchronized} on {@code getTreeType} makes this
 * safe for concurrent use without over-engineering.
 */
public class TreeTypeFactory {

    /** Cache key  →  flyweight instance */
    private final Map<String, ForestTreeType> cache = new HashMap<>();

    // ── Factory method ────────────────────────────────────────────────────────

    /**
     * Returns a flyweight for the given intrinsic-state combination.
     * Creates a new {@link ForestTreeType} only on first request.
     *
     * @param name    species name
     * @param color   dominant color description
     * @param texture texture asset descriptor
     * @return a shared {@link TreeType} instance
     */
    public synchronized TreeType getTreeType(String name, String color, String texture) {
        String key = buildKey(name, color, texture);
        if (!cache.containsKey(key)) {
            cache.put(key, new ForestTreeType(name, color, texture));
        }
        return cache.get(key);
    }

    // ── Diagnostics ───────────────────────────────────────────────────────────

    /** Returns the number of distinct flyweight objects currently in the pool. */
    public int getCacheSize() {
        return cache.size();
    }

    /** Returns an unmodifiable view of all cached flyweights. */
    public Collection<ForestTreeType> getCachedFlyweights() {
        return Collections.unmodifiableCollection(cache.values());
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private String buildKey(String name, String color, String texture) {
        return name + "_" + color + "_" + texture;
    }
}
