package domainModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        /*Observer mockObserver = Mockito.mock(Observer.class);
        Flight flight = new Flight("F101", new Aircraft("A123", "Boeing 737", 150), "JFK", "LAX", "10:00", 150);
        flight.registerObserver(mockObserver);
        Flight mockFlight = Mockito.mock(Flight.class);
        passenger.update(mockFlight, 0);
        Flight flight = new Flight("F101", new Aircraft("A123", "Boeing 737", 10), "JFK", "LAX", "10:00", 150);
        String message = "Notification to Alice: No available seats left for Flight F101";
        //passenger.update(flight, 0);
        System.setOut(new PrintStream(outContent));
        int day = 1;
        flight.notifyObservers(day);
        String output = outContent.toString();
        assertTrue(output.contains(message));*/
        Flight flight = new Flight("F101", new Aircraft("A123", "Boeing 737", 10), "JFK", "LAX", "10:00", 150);
        Passenger passenger = new Passenger(1, "Alice");

        // Prenota un posto disponibile
        flight.bookSeat(1, passenger);

        // Ora, notifica gli osservatori
        int day = 1;
        System.setOut(new PrintStream(outContent));
        flight.notifyObservers(day);

        // Verifica che il messaggio sia stampato
        String output = outContent.toString();
        assertFalse(output.contains("Notification to Alice: No available seats left for Flight F101"));
    }

    @Test
    void testToString() {
        assertEquals("Passenger{code='1', name='Alice'}", passenger.toString());
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
}