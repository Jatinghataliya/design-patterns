package behavioral.observer.subject;

import behavioral.observer.observers.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Subject: YouTube Channel.
 * Maintains a list of subscribers (observers) and notifies them when a new video is uploaded.
 */
public class YouTubeChannel implements Subject {
    private final String channelName;
    private final List<Observer> subscribers = new ArrayList<>();
    private final List<String> uploadedVideos = new ArrayList<>();

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Observer observer) {
        if (observer != null && !subscribers.contains(observer)) {
            subscribers.add(observer);
            System.out.printf("[%s] -> Added subscriber: '%s'%n", channelName, observer.getSubscriberName());
        }
    }

    @Override
    public void unsubscribe(Observer observer) {
        if (subscribers.remove(observer)) {
            System.out.printf("[%s] -> Removed subscriber: '%s'%n", channelName, observer.getSubscriberName());
        }
    }

    @Override
    public void notifyObservers(String videoTitle, String videoUrl) {
        System.out.printf("%n[%s] Notifying %d subscriber(s) about \"%s\"...%n",
                channelName, subscribers.size(), videoTitle);
        for (Observer observer : subscribers) {
            observer.update(channelName, videoTitle, videoUrl);
        }
    }

    /**
     * Business method: Uploading a new video triggers notifications.
     */
    public void uploadVideo(String title, String videoId) {
        uploadedVideos.add(title);
        String url = "https://youtu.be/" + videoId;
        System.out.printf("%n=== [%s] Uploaded new video: \"%s\" ===%n", channelName, title);
        notifyObservers(title, url);
    }

    public String getChannelName() {
        return channelName;
    }

    public int getSubscriberCount() {
        return subscribers.size();
    }
}
