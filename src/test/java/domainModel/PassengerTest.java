package domainModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {
    private Passenger passenger;

    @BeforeEach
    void setUp(){
        passenger = new Passenger(1, "Alice");
    }
    @Test
    void getCode() {
        assertEquals(1, passenger.getCode());
    }

    @Test
    void makeReservation() {
        Flight flight = new Flight("F101", new Aircraft("A123", "Boeing 737", 150), "JFK", "LAX", "10:00", 150);
        int day = 1;
        passenger.makeReservation(flight, day);
    }

    @Test
    void update() {
        // Creare un oggetto Flight per simulare la notifica
        Flight flight = new Flight("F101", new Aircraft("A123", "Boeing 737", 150), "JFK", "LAX", "10:00", 150);
        int availableSeats = 0;
        passenger.update(flight, availableSeats);
    }

    @Test
    void testToString() {
        assertEquals("Passenger{code='1', name='Alice'}", passenger.toString());
    }
}