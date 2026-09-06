package behavioral.observer.observers;

/**
 * Observer Interface.
 * Declares the update method called by subjects when an event occurs.
 */
public interface Observer {
    void update(String channelName, String videoTitle, String videoUrl);
    String getSubscriberName();
}
