package behavioral.mediator.mediator;

import behavioral.mediator.colleague.Flight;

/**
 * Mediator Interface: AirTrafficControl
 * Declares communication methods used by flights to coordinate with the control tower.
 */
public interface AirTrafficControl {
    void registerFlight(Flight flight);
    boolean requestLandingClearance(Flight flight);
    boolean requestTakeoffClearance(Flight flight);
    void reportRunwayVacated(Flight flight, String action);
    void broadcastEmergency(Flight flight, String emergencyDetails);
}
