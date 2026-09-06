package structural.facade;

import structural.facade.facade.HomeTheaterFacade;
import structural.facade.subsystem.*;

/**
 * Demo runner for Facade Pattern: Home Theater Orchestration.
 */
public class FacadeDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("            FACADE DESIGN PATTERN DEMO            ");
        System.out.println("          Home Theater Subsystem Control          ");
        System.out.println("==================================================");

        // 1. Initialize Subsystem Components
        SmartLighting lighting = new SmartLighting();
        Projector projector = new Projector();
        ProjectionScreen screen = new ProjectionScreen();
        SurroundSoundAmplifier amplifier = new SurroundSoundAmplifier();
        StreamingPlayer player = new StreamingPlayer();

        // 2. Initialize Facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(
                lighting, projector, screen, amplifier, player
        );

        // 3. Client calls single high-level Facade method to watch movie
        homeTheater.watchMovie("Interstellar (2014) - Christopher Nolan");

        // 4. Client calls single high-level Facade method to power down
        homeTheater.endMovie();
    }
}
