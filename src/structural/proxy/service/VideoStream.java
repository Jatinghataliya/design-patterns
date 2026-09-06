package structural.proxy.service;

/**
 * Subject Interface: VideoStream
 *
 * Defines the contract shared by both the RealSubject and all Proxy types.
 * The client always programs to this interface, making proxies transparent.
 */
public interface VideoStream {

    /**
     * Returns the title of the video.
     */
    String getTitle();

    /**
     * Returns the full video metadata / description.
     * This is the "expensive" operation that triggers lazy loading.
     */
    String getMetadata();

    /**
     * Streams (plays) the video content.
     *
     * @param userRole role of the requesting user (e.g. "FREE", "PREMIUM", "ADMIN")
     */
    void stream(String userRole);

    /**
     * Returns the duration of the video in seconds.
     */
    int getDurationSeconds();
}
