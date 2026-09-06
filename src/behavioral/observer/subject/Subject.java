package behavioral.observer.subject;

import behavioral.observer.observers.Observer;

/**
 * Subject (Observable) Interface.
 * Declares subscription management methods.
 */
public interface Subject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers(String videoTitle, String videoUrl);
}
