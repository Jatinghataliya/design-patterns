package behavioral.mediator;

import behavioral.mediator.colleague.CommercialFlight;
import behavioral.mediator.mediator.AirportControlTower;

/**
 * Demo runner for Mediator Pattern: Air Traffic Control Tower.
 */
public class MediatorDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("          MEDIATOR DESIGN PATTERN DEMO            ");
        System.out.println("       Air Traffic Control Tower Simulation       ");
        System.out.println("==================================================\n");

        // 1. Create the Mediator (JFK Airport Tower, Single Runway 22L)
        AirportControlTower jfkTower = new AirportControlTower("JFK", "22L");

        // 2. Create Aircraft (Colleagues) and register with Tower
        CommercialFlight flightAA101 = new CommercialFlight(jfkTower, "AA-101", "American Airlines");
        CommercialFlight flightDL202 = new CommercialFlight(jfkTower, "DL-202", "Delta Air Lines");
        CommercialFlight flightUA303 = new CommercialFlight(jfkTower, "UA-303", "United Airlines");

        // 3. Scenario 1: Normal Landing Sequence with Concurrent Requests
        System.out.println("\n--- Scenario 1: AA-101 Lands While DL-202 Tries to Take Off ---");
        // AA-101 requests landing -> Cleared & locks runway
        flightAA101.requestLanding();

        // While AA-101 is still on the runway, DL-202 tries to take off
        System.out.println();
        flightDL202.requestTakeoff(); // Should be DENIED because AA-101 occupies runway

        // 4. Scenario 2: Now that runway is vacated, DL-202 can take off
        System.out.println("\n--- Scenario 2: DL-202 Retries Takeoff After Runway is Free ---");
        flightDL202.requestTakeoff(); // Cleared now!

        // 5. Scenario 3: Emergency Broadcast
        System.out.println("\n--- Scenario 3: Emergency Mayday Broadcast by UA-303 ---");
        flightUA303.triggerMayday("Engine #2 flameout, fuel leak");
    }
}
