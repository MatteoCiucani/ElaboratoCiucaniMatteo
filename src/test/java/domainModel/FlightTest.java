package domainModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        Aircraft aircraft = new Aircraft("A123", "Boeing 737", 150);
        flight = new Flight("F101", aircraft, "JFK", "LAX", "10:00", 150);
    }

    @Test
    void getCode() {
        assertEquals("F101", flight.getCode());
    }

    @Test
    void getAircraft() {
        Aircraft aircraft = flight.getAircraft();
        assertEquals("A123", aircraft.getCode());
    }

    @Test
    void getDepartureTime() {
        assertEquals("10:00", flight.getDepartureTime());
    }

    @Test
    void isSeatAvailable() {
        // Inizialmente, tutti i posti dovrebbero essere disponibili
        for (int day = 1; day <= 7; day++) {
            assertTrue(flight.isSeatAvailable(day));
        }

        // Prenotiamo tutti i posti in un giorno specifico
        for (int day = 1; day <= 7; day++) {
            Passenger passenger = new Passenger(day, "PassengerName" + day);
            flight.bookSeat(day, passenger);
        }

        // Dopo la prenotazione di tutti i posti, il metodo dovrebbe restituire false per tutti i giorni
        for (int day = 1; day <= 7; day++) {
            assertFalse(flight.isSeatAvailable(day));
        }
    }

    @Test
    void bookSeat() {
        // Verifica che il metodo decrementi correttamente la disponibilità dei posti
        int day = 1;
        Passenger passenger = new Passenger(1, "Alice");
        int initialAvailableSeats = flight.getAvailableSeats(day);
        flight.bookSeat(day, passenger);
        int updatedAvailableSeats = flight.getAvailableSeats(day);
        assertEquals(initialAvailableSeats - 1, updatedAvailableSeats);

        // Verifica che il metodo aggiunga la prenotazione correttamente
        int passengerCount = flight.getPassengerCountForDay(day);
        assertEquals(1, passengerCount);

        // Verifica che il metodo restituisca false se non ci sono posti disponibili
        for (int i = 2; i <= 150; i++) {
            Passenger newPassenger = new Passenger(i, "PassengerName" + i);
            flight.bookSeat(day, newPassenger);
        }
        passengerCount = flight.getPassengerCountForDay(day);
        assertEquals(150, passengerCount); // 150 passeggeri prenotati
    }

    @Test
    void getPassengerCountForDay() {
        int day = 1;
        Passenger passenger1 = new Passenger(1, "Alice");
        Passenger passenger2 = new Passenger(2, "Matteo");
        Passenger passenger3 = new Passenger(3, "Charlie");

        // Verifica che il conteggio sia inizializzato a 0
        int initialPassengerCount = flight.getPassengerCountForDay(day);
        assertEquals(0, initialPassengerCount);

        // Prenota per il giorno
        flight.bookSeat(day, passenger1);
        flight.bookSeat(day, passenger2);

        // verifica che i passeggeri prenotati siano in numero giusto
        int updatedPassengerCount = flight.getPassengerCountForDay(day);
        assertEquals(2, updatedPassengerCount);

        // Prenota altri posti per il giorno
        flight.bookSeat(day, passenger3);

        // aggiorna il conteggio
        updatedPassengerCount = flight.getPassengerCountForDay(day);
        assertEquals(3, updatedPassengerCount);
    }
}