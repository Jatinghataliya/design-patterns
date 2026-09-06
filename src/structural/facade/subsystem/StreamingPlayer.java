package structural.facade.subsystem;

/**
 * Subsystem 5: Streaming Player (Apple TV / 4K Player).
 */
public class StreamingPlayer {
    public void on() {
        System.out.println("  [StreamingPlayer] Booting 4K Media Player...");
    }

    public void play(String movieTitle) {
        System.out.printf("  [StreamingPlayer] Streaming \"%s\" in 4K HDR with Dolby Vision.%n", movieTitle);
    }

    public void stop() {
        System.out.println("  [StreamingPlayer] Playback stopped.");
    }

    public void off() {
        System.out.println("  [StreamingPlayer] Media Player entered sleep mode.");
    }
}
