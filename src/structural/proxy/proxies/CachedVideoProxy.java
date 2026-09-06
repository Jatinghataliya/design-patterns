package structural.proxy.proxies;

import structural.proxy.service.VideoStream;
import structural.proxy.service.YoutubeVideoStream;

/**
 * Virtual Proxy + Caching Proxy: CachedVideoProxy
 *
 * Wraps a {@link YoutubeVideoStream} and provides two optimisations:
 *
 *  1. LAZY INITIALISATION (Virtual Proxy)
 *     The real {@link YoutubeVideoStream} is NOT created when this proxy is
 *     constructed.  It is only instantiated on the first method call that
 *     actually needs it — deferring the expensive network fetch until necessary.
 *
 *  2. RESULT CACHING (Caching Proxy)
 *     Once the metadata string is fetched from the real object it is cached
 *     here.  Subsequent calls to {@link #getMetadata()} return the cached
 *     value immediately without hitting the real object again.
 *
 * The client sees exactly the same {@link VideoStream} interface in both cases.
 */
public class CachedVideoProxy implements VideoStream {

    // ── Configuration data (known at proxy-creation time) ────────────────────
    private final String videoId;
    private final String title;
    private final String metadata;
    private final int    durationSeconds;
    private final String contentTier;

    // ── Lazily initialised real subject ───────────────────────────────────────
    private YoutubeVideoStream realVideo;   // null until first use

    // ── Metadata cache ────────────────────────────────────────────────────────
    private String cachedMetadata;          // null until first getMetadata() call

    public CachedVideoProxy(String videoId, String title, String metadata,
                            int durationSeconds, String contentTier) {
        this.videoId         = videoId;
        this.title           = title;
        this.metadata        = metadata;
        this.durationSeconds = durationSeconds;
        this.contentTier     = contentTier;
        System.out.printf("  [CachedVideoProxy] Proxy created for videoId='%s'  (real object NOT yet loaded)%n", videoId);
    }

    // ── VideoStream interface ─────────────────────────────────────────────────

    /**
     * Title is cheap — answer directly from local state, no real object needed.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Metadata is expensive — fetch once from the real object, then cache.
     */
    @Override
    public String getMetadata() {
        if (cachedMetadata == null) {
            System.out.printf("  [CachedVideoProxy] Cache MISS for metadata of '%s' — delegating to real object%n", title);
            cachedMetadata = getRealVideo().getMetadata();
        } else {
            System.out.printf("  [CachedVideoProxy] Cache HIT  for metadata of '%s' — returning cached value%n", title);
        }
        return cachedMetadata;
    }

    /**
     * Streaming always delegates to the real object (no caching of a video stream).
     */
    @Override
    public void stream(String userRole) {
        getRealVideo().stream(userRole);
    }

    @Override
    public int getDurationSeconds() {
        return durationSeconds;
    }

    // ── Lazy initialisation helper ────────────────────────────────────────────

    /**
     * Returns the real object, creating it on first call.
     */
    private YoutubeVideoStream getRealVideo() {
        if (realVideo == null) {
            System.out.printf("  [CachedVideoProxy] Lazy-loading real YoutubeVideoStream for videoId='%s'%n", videoId);
            realVideo = new YoutubeVideoStream(videoId, title, metadata, durationSeconds, contentTier);
        }
        return realVideo;
    }
}
