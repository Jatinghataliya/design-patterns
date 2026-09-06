package behavioral.observer;

import behavioral.observer.observers.AnalyticsTracker;
import behavioral.observer.observers.EmailSubscriber;
import behavioral.observer.observers.MobileAppSubscriber;
import behavioral.observer.observers.Observer;
import behavioral.observer.subject.YouTubeChannel;

/**
 * Demo runner for Observer Pattern.
 */
public class ObserverDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("          OBSERVER DESIGN PATTERN DEMO            ");
        System.out.println("==================================================\n");

        // 1. Create the Subject (YouTube Channel)
        YouTubeChannel techLeadChannel = new YouTubeChannel("TechWithJava");

        // 2. Create Observers
        Observer emailSub1 = new EmailSubscriber("Alice Johnson", "alice@example.com");
        Observer emailSub2 = new EmailSubscriber("Bob Miller", "bob@example.com");
        Observer mobileSub = new MobileAppSubscriber("charlie_dev", "token_a98f12c4b789");
        AnalyticsTracker analytics = new AnalyticsTracker();

        // 3. Register Subscribers
        System.out.println("--- 1. Subscribing Observers ---");
        techLeadChannel.subscribe(emailSub1);
        techLeadChannel.subscribe(emailSub2);
        techLeadChannel.subscribe(mobileSub);
        techLeadChannel.subscribe(analytics);

        // 4. Publish first event (Video 1)
        techLeadChannel.uploadVideo("Creational Design Patterns in Java", "v=abc12345");

        // 5. Unsubscribe one observer (Bob unsubscribes)
        System.out.println("\n--- 2. Unsubscribing Bob Miller ---");
        techLeadChannel.unsubscribe(emailSub2);

        // 6. Dynamic Lambda Observer (Custom subscriber without a dedicated class)
        Observer discordBot = new Observer() {
            @Override
            public void update(String channelName, String videoTitle, String videoUrl) {
                System.out.printf("  [Discord Webhook Bot]: Posted announcement in #dev-announcements: %s%n", videoTitle);
            }

            @Override
            public String getSubscriberName() {
                return "Discord Server Bot";
            }
        };
        System.out.println("\n--- 3. Adding Discord Bot Subscriber ---");
        techLeadChannel.subscribe(discordBot);

        // 7. Publish second event (Video 2)
        techLeadChannel.uploadVideo("Behavioral Design Patterns: Observer & Strategy", "v=xyz98765");

        System.out.printf("%nFinal Summary: Channel has %d active subscribers. Analytics recorded %d upload events.%n",
                techLeadChannel.getSubscriberCount(), analytics.getUploadEventCount());
    }
}
