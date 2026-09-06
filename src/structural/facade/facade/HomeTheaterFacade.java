package structural.facade.facade;

import structural.facade.subsystem.*;

/**
 * Facade: HomeTheaterFacade
 * Provides high-level, simplified methods (watchMovie, endMovie, listenToMusic)
 * hiding the complex multi-step orchestration of the 5 underlying subsystems.
 */
public class HomeTheaterFacade {
    private final SmartLighting lighting;
    private final Projector projector;
    private final ProjectionScreen screen;
    private final SurroundSoundAmplifier amplifier;
    private final StreamingPlayer player;

    public HomeTheaterFacade(SmartLighting lighting, Projector projector, ProjectionScreen screen,
                             SurroundSoundAmplifier amplifier, StreamingPlayer player) {
        this.lighting = lighting;
        this.projector = projector;
        this.screen = screen;
        this.amplifier = amplifier;
        this.player = player;
    }

    /**
     * One-button operation: prepares the entire theater and starts movie playback.
     */
    public void watchMovie(String movieTitle) {
        System.out.printf("%n==================================================%n");
        System.out.printf("  [Facade] Preparing Home Theater for \"%s\"...%n", movieTitle);
        System.out.printf("==================================================%n");

        lighting.dim(10);
        screen.down();
        projector.on();
        projector.setInput("HDMI-1 (Streaming Player)");
        projector.setWideScreenMode();
        amplifier.on();
        amplifier.setSurroundAudio();
        amplifier.setVolume(65);
        player.on();
        player.play(movieTitle);

        System.out.println(">>> Home Theater Ready! Enjoy your movie! <<<\n");
    }

    /**
     * One-button operation: shuts down all equipment and restores room lighting.
     */
    public void endMovie() {
        System.out.println("\n==================================================");
        System.out.println("  [Facade] Shutting down Home Theater System...   ");
        System.out.println("==================================================");

        player.stop();
        player.off();
        amplifier.off();
        projector.off();
        screen.up();
        lighting.on();

        System.out.println(">>> Theater System is OFF. Lights restored. <<<\n");
    }
}
