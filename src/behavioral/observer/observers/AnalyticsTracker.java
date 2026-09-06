package behavioral.observer.observers;

/**
 * Concrete Observer 3: Analytics / Metric Observer.
 * Tracks metrics every time a video is uploaded.
 */
public class AnalyticsTracker implements Observer {
    private int uploadEventCount = 0;

    @Override
    public void update(String channelName, String videoTitle, String videoUrl) {
        uploadEventCount++;
        System.out.printf("  [Analytics Engine]: Tracked event #%d for channel '%s'. Title length: %d chars.%n",
                uploadEventCount, channelName, videoTitle.length());
    }

    @Override
    public String getSubscriberName() {
        return "Internal Analytics Engine";
    }

    public int getUploadEventCount() {
        return uploadEventCount;
    }
}
