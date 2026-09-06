package behavioral.mediator.colleague;

import behavioral.mediator.mediator.AirTrafficControl;

/**
 * Base Abstract Colleague: Flight.
 * Holds a reference to the AirTrafficControl mediator and communicates ONLY through it.
 */
public abstract class Flight {
    protected final AirTrafficControl atc;
    protected final String flightNumber;
    protected final String airline;

    public Flight(AirTrafficControl atc, String flightNumber, String airline) {
        this.atc = atc;
        this.flightNumber = flightNumber;
        this.airline = airline;
        // Auto-register with the mediator
        atc.registerFlight(this);
    }

    public abstract void requestLanding();
    public abstract void requestTakeoff();
    public abstract void receiveNotice(String from, String message);

    public String getFlightNumber() { return flightNumber; }
    public String getAirline() { return airline; }

    @Override
    public String toString() {
        return flightNumber + " (" + airline + ")";
    }
}
