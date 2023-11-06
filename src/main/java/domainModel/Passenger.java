package domainModel;
import java.util.ArrayList;
import java.util.List;
public class Passenger implements Observer{
    private int code;
    private String name;
    private List<Reservation> reservations = new ArrayList<>();
    private List<Integer> reservedDays = new ArrayList<>();

    public Passenger(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void makeReservation(Flight flight, int day) {
        if (flight.isSeatAvailable(day)) {
            Reservation reservation = new Reservation(flight, this, day);
            reservations.add(reservation);
            reservedDays.add(day); // Aggiungi il giorno alla lista dei giorni prenotati
            flight.registerObserver(this);
            System.out.println(name + " has successfully made a reservation for Flight " + flight.getCode() +
                    " on day " + day);
        } else {
            System.out.println("Sorry, no available seats for Flight " + flight.getCode() + " on day " + day);
        }
    }

    @Override
    public void update(Flight flight, int availableSeats) {
        if (availableSeats == 0) {
            System.out.println("Notification to " + name + ": No available seats left for Flight " + flight.getCode());
        }
    }

    /*public List<Reservation> getReservations() {
        return reservations;
    }*/

    @Override
    public String toString() {
        return "Passenger{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
