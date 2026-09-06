package behavioral.observer.observers;

/**
 * Concrete Observer 1: Email Subscriber.
 * Receives email notifications when a new video is published.
 */
public class EmailSubscriber implements Observer {
    private final String name;
    private final String email;

    public EmailSubscriber(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public void update(String channelName, String videoTitle, String videoUrl) {
        System.out.printf("  [Email Notification -> %s (%s)]: New video on %s! \"%s\" -> %s%n",
                name, email, channelName, videoTitle, videoUrl);
    }

    @Override
    public String getSubscriberName() {
        return name;
    }
}
