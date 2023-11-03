package domainModel;
public class Reservation {
    private Flight flight;
    private Passenger passenger;
    private int day;

    public Reservation(Flight flight, Passenger passenger, int day) {
        this.flight = flight;
        this.passenger = passenger;
        this.day = day;
        flight.bookSeat(day, passenger);
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public int getDay() {
        return day;
    }
}
