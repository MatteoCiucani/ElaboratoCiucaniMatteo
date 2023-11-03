package domainModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AircraftTest {
    private Aircraft aircraft;

    @BeforeEach
    void setUp() {
        aircraft = new Aircraft("A123", "Boeing 737", 150);
    }

    @Test
    void getCode() {
        assertEquals("A123", aircraft.getCode());
    }

    @Test
    void getTotalSeats() {
        assertEquals(150, aircraft.getTotalSeats());
    }
}