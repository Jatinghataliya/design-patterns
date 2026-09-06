package behavioral.mediator.colleague;

import behavioral.mediator.mediator.AirTrafficControl;

/**
 * Concrete Colleague: Commercial Flight (e.g. Passenger Airplane).
 */
public class CommercialFlight extends Flight {

    public CommercialFlight(AirTrafficControl atc, String flightNumber, String airline) {
        super(atc, flightNumber, airline);
    }

    @Override
    public void requestLanding() {
        System.out.printf("[%s] Requesting landing clearance from Tower...%n", flightNumber);
        boolean cleared = atc.requestLandingClearance(this);
        if (cleared) {
            System.out.printf("  ==> [%s] TOUCHDOWN! Landed safely. Vacating runway.%n", flightNumber);
            atc.reportRunwayVacated(this, "LANDING");
        } else {
            System.out.printf("  ==> [%s] Holding at 5,000 ft in holding pattern.%n", flightNumber);
        }
    }

    @Override
    public void requestTakeoff() {
        System.out.printf("[%s] Requesting takeoff clearance from Tower...%n", flightNumber);
        boolean cleared = atc.requestTakeoffClearance(this);
        if (cleared) {
            System.out.printf("  ==> [%s] AIRBORNE! Took off smoothly. Ascending to 30,000 ft.%n", flightNumber);
            atc.reportRunwayVacated(this, "TAKEOFF");
        } else {
            System.out.printf("  ==> [%s] Holding short of runway on taxiway.%n", flightNumber);
        }
    }

    @Override
    public void receiveNotice(String from, String message) {
        System.out.printf("    [%s Radio Alert]: Received update from [%s] -> \"%s\"%n",
                flightNumber, from, message);
    }

    public void triggerMayday(String reason) {
        System.out.printf("%n*** [%s] TRANSMITTING MAYDAY MAYDAY! Reason: %s ***%n", flightNumber, reason);
        atc.broadcastEmergency(this, reason);
    }
}
