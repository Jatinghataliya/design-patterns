package structural.proxy;

import structural.proxy.proxies.CachedVideoProxy;
import structural.proxy.proxies.ProtectedVideoProxy;
import structural.proxy.service.VideoStream;

/**
 * Demo runner for the Proxy Pattern: Video Streaming Service.
 *
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │                          PROXY PATTERN                                  │
 * │                                                                         │
 * │  Intent: Provide a surrogate or placeholder for another object to       │
 * │  control access to it.                                                  │
 * │                                                                         │
 * │  Participants:                                                           │
 * │   • VideoStream          – Subject interface                            │
 * │   • YoutubeVideoStream   – Real Subject  (expensive, heavyweight)       │
 * │   • CachedVideoProxy     – Virtual + Caching Proxy (lazy init + cache)  │
 * │   • ProtectedVideoProxy  – Protection Proxy (role-based access control) │
 * └─────────────────────────────────────────────────────────────────────────┘
 *
 * Scenario
 * ────────
 * A video streaming platform serves both FREE and PREMIUM videos.
 * Loading video metadata from the remote API is expensive (~800 ms).
 *
 *  • CachedVideoProxy   – delays real object creation until the first actual
 *                         access and caches expensive metadata thereafter.
 *  • ProtectedVideoProxy – wraps any VideoStream and gates stream() calls
 *                         based on the user's subscription tier.
 *
 * Both proxies implement VideoStream so the client code is identical
 * regardless of which implementation it talks to.
 */
public class ProxyDemo {

    public static void main(String[] args) throws InterruptedException {

        printHeader();

        // ══════════════════════════════════════════════════════════════════════
        // PART 1 – Virtual + Caching Proxy
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  PART 1 — Virtual Proxy + Caching Proxy");
        System.out.println("  (CachedVideoProxy — lazy init & metadata cache)");
        System.out.println("══════════════════════════════════════════════════");

        // Proxy is created instantly — no network call yet
        VideoStream videoA = new CachedVideoProxy(
                "dQw4w9WgXcQ",
                "Clean Code: Writing Maintainable Software",
                "A deep-dive into SOLID principles and refactoring techniques.",
                3600, "FREE"
        );

        System.out.println("\n--- 1a. getTitle() — answered from proxy, no real object needed ---");
        System.out.println("  Title: " + videoA.getTitle());

        System.out.println("\n--- 1b. getMetadata() — first call triggers lazy load + remote fetch ---");
        long t1 = System.currentTimeMillis();
        System.out.println("  Metadata: " + videoA.getMetadata());
        System.out.printf("  Time taken: %d ms%n", System.currentTimeMillis() - t1);

        System.out.println("\n--- 1c. getMetadata() — second call returns cached result instantly ---");
        long t2 = System.currentTimeMillis();
        System.out.println("  Metadata: " + videoA.getMetadata());
        System.out.printf("  Time taken: %d ms%n", System.currentTimeMillis() - t2);

        System.out.println("\n--- 1d. stream() — delegates to real object ---");
        videoA.stream("FREE");

        // ══════════════════════════════════════════════════════════════════════
        // PART 2 – Protection Proxy
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  PART 2 — Protection Proxy");
        System.out.println("  (ProtectedVideoProxy — role-based access control)");
        System.out.println("══════════════════════════════════════════════════");

        // FREE video — wrap in protection proxy
        VideoStream freeVideo = new ProtectedVideoProxy(
                new CachedVideoProxy("abc123", "Introduction to Java",
                        "Beginner-friendly Java programming tutorial.", 1800, "FREE"),
                "FREE"
        );

        // PREMIUM video — wrap in protection proxy
        VideoStream premiumVideo = new ProtectedVideoProxy(
                new CachedVideoProxy("xyz789", "Advanced System Design Masterclass",
                        "Enterprise-scale system design patterns and case studies.", 7200, "PREMIUM"),
                "PREMIUM"
        );

        System.out.println("\n--- 2a. FREE user streams FREE content (allowed) ---");
        freeVideo.stream("FREE");

        System.out.println("\n--- 2b. FREE user streams PREMIUM content (denied) ---");
        premiumVideo.stream("FREE");

        System.out.println("\n--- 2c. PREMIUM user streams PREMIUM content (allowed) ---");
        premiumVideo.stream("PREMIUM");

        System.out.println("\n--- 2d. ADMIN user streams PREMIUM content (allowed) ---");
        premiumVideo.stream("ADMIN");

        System.out.println("\n--- 2e. PREMIUM user streams FREE content (allowed) ---");
        freeVideo.stream("PREMIUM");

        // ══════════════════════════════════════════════════════════════════════
        // PART 3 – Proxy chaining (Virtual + Protection stacked)
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  PART 3 — Proxy Chaining");
        System.out.println("  (CachedVideoProxy wrapped inside ProtectedVideoProxy)");
        System.out.println("══════════════════════════════════════════════════");

        VideoStream stackedProxy = new ProtectedVideoProxy(
                new CachedVideoProxy("stk001", "Machine Learning Fundamentals",
                        "From linear regression to neural networks.", 5400, "PREMIUM"),
                "PREMIUM"
        );

        System.out.println("\n--- 3a. getTitle() — no real object, no auth check needed ---");
        System.out.println("  Title: " + stackedProxy.getTitle());

        System.out.println("\n--- 3b. FREE user tries to stream PREMIUM stacked proxy content (denied) ---");
        stackedProxy.stream("FREE");

        System.out.println("\n--- 3c. PREMIUM user streams — triggers lazy load through both proxies ---");
        stackedProxy.stream("PREMIUM");

        System.out.println("\n--- 3d. PREMIUM user streams again — real object already cached ---");
        stackedProxy.stream("PREMIUM");

        printSummary();
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private static void printHeader() {
        System.out.println("==================================================");
        System.out.println("           PROXY DESIGN PATTERN DEMO              ");
        System.out.println("         Video Streaming Service Platform         ");
        System.out.println("==================================================");
    }

    private static void printSummary() {
        System.out.println("\n==================================================");
        System.out.println("  PROXY PATTERN — KEY TAKEAWAYS");
        System.out.println("==================================================");
        System.out.println("  Virtual Proxy    : Defers expensive object creation");
        System.out.println("                    until the first real access.");
        System.out.println("  Caching Proxy    : Stores results of costly operations");
        System.out.println("                    and returns them on repeat calls.");
        System.out.println("  Protection Proxy : Gates method calls with access-");
        System.out.println("                    control logic before delegating.");
        System.out.println("  Proxy Chaining   : Multiple proxies can be stacked;");
        System.out.println("                    each adds one cross-cutting concern.");
        System.out.println("  Client is UNAWARE of which implementation it uses —");
        System.out.println("  all proxies honour the same VideoStream interface.");
        System.out.println("==================================================");
    }
}
