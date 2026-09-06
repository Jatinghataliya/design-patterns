package structural.proxy.service;

/**
 * Real Subject: YoutubeVideoStream
 *
 * The actual, heavyweight service object.
 * Construction simulates an expensive network round-trip to fetch video
 * metadata from a remote API (e.g. YouTube Data API).
 *
 * In a real system this class might:
 *  - Open a database connection
 *  - Deserialise a large JSON payload
 *  - Decode a video manifest
 *
 * The Virtual Proxy will defer creating this object until it is truly needed.
 */
public class YoutubeVideoStream implements VideoStream {

    private final String videoId;
    private final String title;
    private final String metadata;
    private final int    durationSeconds;
    private final String contentTier;   // "FREE" or "PREMIUM"

    /**
     * Constructor performs the "expensive" initialisation.
     * A simulated 800 ms network delay makes the cost visible in the demo.
     */
    public YoutubeVideoStream(String videoId, String title,
                              String metadata, int durationSeconds,
                              String contentTier) {
        this.videoId         = videoId;
        this.title           = title;
        this.metadata        = metadata;
        this.durationSeconds = durationSeconds;
        this.contentTier     = contentTier;

        // Simulate expensive remote fetch
        System.out.printf("  [YoutubeVideoStream] Fetching video data from remote API for videoId='%s'...%n", videoId);
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
        System.out.printf("  [YoutubeVideoStream] Data loaded  ─  title='%s'  tier=%s  duration=%ds%n",
                title, contentTier, durationSeconds);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getMetadata() {
        return String.format("VideoId: %s | Title: %s | Duration: %ds | Tier: %s | Info: %s",
                videoId, title, durationSeconds, contentTier, metadata);
    }

    @Override
    public void stream(String userRole) {
        System.out.printf("  [YoutubeVideoStream] ▶ Streaming '%s' [%s] to %s user%n",
                title, contentTier, userRole);
    }

    @Override
    public int getDurationSeconds() {
        return durationSeconds;
    }

    public String getContentTier() {
        return contentTier;
    }
}
