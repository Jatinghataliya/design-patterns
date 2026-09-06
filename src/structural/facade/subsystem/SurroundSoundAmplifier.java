package structural.facade.subsystem;

/**
 * Subsystem 4: Surround Sound Amplifier / Audio Receiver.
 */
public class SurroundSoundAmplifier {
    public void on() {
        System.out.println("  [Amplifier] Powering ON Dolby Atmos 7.1.4 Audio Amplifier...");
    }

    public void setSurroundAudio() {
        System.out.println("  [Amplifier] Audio decoder mode set to Dolby Atmos 3D Surround.");
    }

    public void setVolume(int level) {
        System.out.printf("  [Amplifier] Master volume set to %d dB%n", level);
    }

    public void off() {
        System.out.println("  [Amplifier] Powering OFF Surround Sound Amplifier.");
    }
}
