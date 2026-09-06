package behavioral.mediator.mediator;

import behavioral.mediator.colleague.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Mediator: AirportControlTower
 * Coordinates landing, takeoff, runway reservations, and notifications between all aircraft.
 * Flights never talk to each other directly; they only communicate with this tower.
 */
public class AirportControlTower implements AirTrafficControl {
    private final String airportCode;
    private final String runwayId;
    private final List<Flight> registeredFlights = new ArrayList<>();
    private boolean isRunwayOccupied = false;
    private Flight currentRunwayOccupant = null;

    public AirportControlTower(String airportCode, String runwayId) {
        this.airportCode = airportCode;
        this.runwayId = runwayId;
    }

    @Override
    public void registerFlight(Flight flight) {
        if (!registeredFlights.contains(flight)) {
            registeredFlights.add(flight);
            System.out.printf("[Tower - %s] Registered flight %s in airspace.%n", airportCode, flight);
        }
    }

    @Override
    public boolean requestLandingClearance(Flight flight) {
        System.out.printf("[Tower - %s] Processing landing request for %s on Runway %s...%n",
                airportCode, flight.getFlightNumber(), runwayId);

        if (isRunwayOccupied) {
            System.out.printf("  [DENIED] Runway %s is currently OCCUPIED by %s. %s, enter holding pattern.%n",
                    runwayId, currentRunwayOccupant.getFlightNumber(), flight.getFlightNumber());
            return false;
        }

        // Grant clearance and lock runway
        isRunwayOccupied = true;
        currentRunwayOccupant = flight;
        System.out.printf("  [CLEARED] %s, Runway %s is clear. Cleared to land! Wind 270 at 10 knots.%n",
                flight.getFlightNumber(), runwayId);

        // Notify other flights in airspace
        notifyOtherFlights(flight, "Runway " + runwayId + " reserved for landing of " + flight.getFlightNumber());
        return true;
    }

    @Override
    public boolean requestTakeoffClearance(Flight flight) {
        System.out.printf("[Tower - %s] Processing takeoff request for %s on Runway %s...%n",
                airportCode, flight.getFlightNumber(), runwayId);

        if (isRunwayOccupied) {
            System.out.printf("  [DENIED] Runway %s is currently OCCUPIED by %s. %s, hold position.%n",
                    runwayId, currentRunwayOccupant.getFlightNumber(), flight.getFlightNumber());
            return false;
        }

        isRunwayOccupied = true;
        currentRunwayOccupant = flight;
        System.out.printf("  [CLEARED] %s, Runway %s is clear. Cleared for takeoff! Climb and maintain 10,000 ft.%n",
                flight.getFlightNumber(), runwayId);

        notifyOtherFlights(flight, "Runway " + runwayId + " reserved for departure of " + flight.getFlightNumber());
        return true;
    }

    @Override
    public void reportRunwayVacated(Flight flight, String action) {
        if (flight.equals(currentRunwayOccupant)) {
            isRunwayOccupied = false;
            currentRunwayOccupant = null;
            System.out.printf("[Tower - %s] %s completed %s and vacated Runway %s. Runway is now FREE.%n",
                    airportCode, flight.getFlightNumber(), action, runwayId);
            notifyOtherFlights(flight, "Runway " + runwayId + " is now open.");
        }
    }

    @Override
    public void broadcastEmergency(Flight emergencyFlight, String emergencyDetails) {
        System.out.printf("[Tower - %s EMERGENCY PROTOCOL] All aircraft hold! Priority landing granted to %s!%n",
                airportCode, emergencyFlight.getFlightNumber());

        for (Flight flight : registeredFlights) {
            if (!flight.equals(emergencyFlight)) {
                flight.receiveNotice("TOWER EMERGENCY",
                        "HOLD AIRSPACE: Priority emergency landing in progress for " + emergencyFlight.getFlightNumber()
                                + " (" + emergencyDetails + ")");
            }
        }
    }

    private void notifyOtherFlights(Flight originator, String message) {
        for (Flight flight : registeredFlights) {
            if (!flight.equals(originator)) {
                flight.receiveNotice("Tower", message);
            }
        }
    }
}
