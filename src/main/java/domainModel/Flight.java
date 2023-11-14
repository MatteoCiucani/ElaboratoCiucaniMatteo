package domainModel;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Flight implements Subject{
    private String code;
    private Aircraft aircraft;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private int totalSeats;
    private Map<Integer, Integer> seatAvailability = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();
    private Map<Integer, List<Passenger>> dayReservations  = new HashMap<>();
    private Map<Integer, Integer> dayReservationsCount = new HashMap<>();

    public Flight(String code, Aircraft aircraft, String departureAirport, String arrivalAirport, String departureTime, int totalSeats) {
        this.code = code;
        this.aircraft = aircraft;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        for (int i = 1;i <= 7; i++) {
            seatAvailability.put(i, totalSeats);
            dayReservationsCount.put(i, 0);
            dayReservations.put(i, new ArrayList<>());
        }

    }

    public String getCode() {
        return code;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    /*public int getTotalSeats() {
        return totalSeats;
    }*/

    public int getAvailableSeats(int day) {
        return seatAvailability.get(day);
    }

    public boolean isSeatAvailable(int day) {
        return seatAvailability.get(day) > 0;
    }

    public void bookSeat(int day, Passenger passenger) {
        if (seatAvailability.containsKey(day)) {
            seatAvailability.put(day, seatAvailability.get(day) - 1);
            // Inizializza la lista delle prenotazioni per il giorno se non esiste
            dayReservations.putIfAbsent(day, new ArrayList<>());
            dayReservations.get(day).add(passenger);
            notifyObservers(day);
        }

    }
    public int getPassengerCountForDay(int day) {
        //return dayReservations.get(day).size();
        List<Passenger> reservations = dayReservations.get(day);
        return reservations != null ? reservations.size() : 0;
    }


    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int day) {
        for (Observer observer : observers) {
            observer.update(this, getAvailableSeats(day));
        }
    }
}

