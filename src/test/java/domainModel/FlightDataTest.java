package domainModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FlightDataTest {
    private FlightData flightData;

    @BeforeEach
    void setUp() {
        flightData = FlightData.getInstance();
    }

    @Test
    void getInstance() {
        // Verifica che due chiamate a getInstance restituiscano la stessa istanza
        FlightData instance1 = FlightData.getInstance();
        FlightData instance2 = FlightData.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    void addAircraft() {
        // Verifica che un aereo venga aggiunto correttamente
        flightData.addAircraft("A123", "Boeing 737", 150);
        Aircraft aircraft = flightData.getAircraftByCode("A123");
        assertEquals("A123", aircraft.getCode());
        assertEquals("Boeing 737", aircraft.getModel());
        assertEquals(150, aircraft.getTotalSeats());
    }

    @Test
    void addFlight() {
        // Verifica che un volo venga aggiunto correttamente
        flightData.addAircraft("A123", "Boeing 737", 150);
        flightData.addFlight("F101", "A123", "JFK", "LAX", "10:00");
        Flight flight = flightData.getFlightByCode("F101");
        assertEquals("F101", flight.getCode());
        assertEquals("A123", flight.getAircraft().getCode());
        assertEquals("JFK", flight.getDepartureAirport());
        assertEquals("LAX", flight.getArrivalAirport());
        assertEquals("10:00", flight.getDepartureTime());

    }

    @Test
    void setFareForFlight() {
        // Verifica che la tariffa per un volo venga impostata correttamente
        flightData.addAircraft("A123", "Boeing 737", 150);
        flightData.addFlight("F101", "A123", "JFK", "LAX", "10:00");
        flightData.setFareForFlight("F101", 200.0);
        double fare = flightData.getFareForFlight("F101");
        assertEquals(200.0, fare, 0.001);

    }


    @Test
    void getFlightsSortedByDepartureTime() {
        // Verifica che i voli siano ordinati correttamente per ora di partenza
        flightData.addAircraft("A123", "Boeing 737", 150);
        flightData.addAircraft("B456", "Airbus A320", 120);
        flightData.addAircraft("A121", "Boeing 747", 180 );
        flightData.addFlight("F101", "A123", "JFK", "LAX", "10:00");
        flightData.addFlight("F102", "B456", "LAX", "JFK", "14:00");
        flightData.addFlight("F103", "A121", "SFO", "ORD", "08:00");
        List<Flight> flights = flightData.getFlightsSortedByDepartureTime();
        assertEquals("F103", flights.get(0).getCode());
        assertEquals("F101", flights.get(1).getCode());
        assertEquals("F102", flights.get(2).getCode());
    }

    @Test
    void getFlightByCode() {
        // Verifica che il volo venga recuperato correttamente tramite il codice
        flightData.addAircraft("A123", "Boeing 737", 150);
        flightData.addFlight("F101", "A123", "JFK", "LAX", "10:00");
        Flight flight = flightData.getFlightByCode("F101");
        assertEquals("F101", flight.getCode());

    }
}