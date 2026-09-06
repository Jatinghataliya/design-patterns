package structural.proxy.proxies;

import structural.proxy.service.VideoStream;

import java.util.Set;

/**
 * Protection Proxy: ProtectedVideoProxy
 *
 * Wraps any {@link VideoStream} and enforces role-based access control
 * BEFORE delegating calls to the wrapped subject.
 *
 * Access rules
 * ─────────────
 *  • getTitle()       — allowed to everyone
 *  • getMetadata()    — allowed to everyone
 *  • getDuration()    — allowed to everyone
 *  • stream()         — FREE users can only stream FREE-tier content
 *                       PREMIUM / ADMIN users can stream any content
 *
 * The actual content-tier information is read from the inner subject's
 * metadata so this proxy stays decoupled from implementation details.
 */
public class ProtectedVideoProxy implements VideoStream {

    private static final Set<String> PRIVILEGED_ROLES = Set.of("PREMIUM", "ADMIN");

    private final VideoStream inner;        // the guarded subject (could itself be a proxy)
    private final String      contentTier;  // "FREE" or "PREMIUM" — injected at construction

    public ProtectedVideoProxy(VideoStream inner, String contentTier) {
        this.inner       = inner;
        this.contentTier = contentTier;
    }

    // ── VideoStream interface ─────────────────────────────────────────────────

    @Override
    public String getTitle() {
        return inner.getTitle();
    }

    @Override
    public String getMetadata() {
        return inner.getMetadata();
    }

    @Override
    public int getDurationSeconds() {
        return inner.getDurationSeconds();
    }

    /**
     * Access-controlled streaming.
     *
     * @param userRole the role of the requesting user
     * @throws SecurityException if the user's role does not permit streaming this content
     */
    @Override
    public void stream(String userRole) {
        if (!isAccessAllowed(userRole)) {
            System.out.printf("  [ProtectedVideoProxy] ✖ ACCESS DENIED  — '%s' is PREMIUM content." +
                              " User role '%s' is not authorised. Upgrade to PREMIUM to watch.%n",
                              inner.getTitle(), userRole);
            return;
        }
        System.out.printf("  [ProtectedVideoProxy] ✔ Access granted for role='%s' to '%s' [%s]%n",
                userRole, inner.getTitle(), contentTier);
        inner.stream(userRole);
    }

    // ── Access-control logic ──────────────────────────────────────────────────

    /**
     * Returns {@code true} if the given role is allowed to stream this content.
     * FREE content is open to all; PREMIUM content requires a privileged role.
     */
    private boolean isAccessAllowed(String userRole) {
        if ("FREE".equalsIgnoreCase(contentTier)) {
            return true;   // free content → no restriction
        }
        return PRIVILEGED_ROLES.contains(userRole.toUpperCase());
    }
}
