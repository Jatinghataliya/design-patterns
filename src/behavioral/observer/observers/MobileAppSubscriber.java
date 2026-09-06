package behavioral.observer.observers;

/**
 * Concrete Observer 2: Mobile Push Notification Subscriber.
 */
public class MobileAppSubscriber implements Observer {
    private final String username;
    private final String deviceToken;

    public MobileAppSubscriber(String username, String deviceToken) {
        this.username = username;
        this.deviceToken = deviceToken;
    }

    @Override
    public void update(String channelName, String videoTitle, String videoUrl) {
        System.out.printf("  [Mobile Push -> @%s (Device %s)]: \uD83D\uDD14 %s uploaded: %s%n",
                username, deviceToken.substring(0, Math.min(8, deviceToken.length())) + "...",
                channelName, videoTitle);
    }

    @Override
    public String getSubscriberName() {
        return username;
    }
}
