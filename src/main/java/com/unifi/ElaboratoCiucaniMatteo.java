package com.unifi;
import domainModel.Aircraft;
import domainModel.FlightData;
import domainModel.Flight;
import domainModel.Passenger;
import java.util.List;


public class ElaboratoCiucaniMatteo {
    public static void main(String[] args) {
        FlightData flightData = FlightData.getInstance();

        Passenger passenger1 = new Passenger(112, "Alice");
        Passenger passenger2 = new Passenger(117, "Bob");
        Passenger passenger3 = new Passenger(298, "Marco");
        Passenger passenger4 = new Passenger(315, "Matteo");
        Passenger passenger5 = new Passenger(127, "Massimo");
        Passenger passenger6 = new Passenger(221, "Elia");
        Passenger passenger7 = new Passenger(345, "Lorenzo");
        Passenger passenger8 = new Passenger(441, "Jon");
        Passenger passenger9 = new Passenger(657, "Ginevra");

        flightData.addAircraft("A123", "Boeing 737", 100);
        flightData.addAircraft("B456", "Airbus A320", 120);
        flightData.addAircraft("A121", "Boeing 747", 180);

        Aircraft aircraftF101 = flightData.getAircraftByCode("A123");
        Aircraft aircraftF102 = flightData.getAircraftByCode("B456");
        Aircraft aircraftF103 = flightData.getAircraftByCode("A121");

        flightData.addFlight("F101", "A123", "JFK", "LAX", "10:00");
        flightData.addFlight("F102", "B456", "LAX", "JFK", "14:00");
        flightData.addFlight("F103", "A121", "SFO", "ORD", "08:00");

        System.out.println("Seats for flight:");
        System.out.println("Seats for flight F101: " + aircraftF101.getTotalSeats());
        System.out.println("Seats for flight F102: " + aircraftF102.getTotalSeats());
        System.out.println("Seats for flight F103: " + aircraftF103.getTotalSeats());

        System.out.println("Fare for flight:");
        flightData.setFareForFlight("F101", 200.0);
        flightData.setFareForFlight("F102", 250.0);
        flightData.setFareForFlight("F103", 180.0);

        // Ottieni la tariffa di un volo specifico
        double fareForF101 = flightData.getFareForFlight("F101");
        double fareForF102 = flightData.getFareForFlight("F102");
        double fareForF103 = flightData.getFareForFlight("F103");

        System.out.println("Fare for F101: $" + fareForF101);
        System.out.println("Fare for F102: $" + fareForF102);
        System.out.println("Fare for F103: $" + fareForF103);


        List<Flight> flights = flightData.getFlightsSortedByDepartureTime();
        System.out.println("Flights sorted by departure time:");
        for (Flight flight : flights) {
            System.out.println(flight.getCode() + " - " + flight.getDepartureTime());
        }


        passenger1.makeReservation(flights.get(0), 1);
        passenger2.makeReservation(flights.get(2), 1);
        passenger3.makeReservation(flights.get(0), 1);
        passenger4.makeReservation(flights.get(0), 3);
        passenger5.makeReservation(flights.get(2), 6);
        passenger6.makeReservation(flights.get(0), 6);
        passenger7.makeReservation(flights.get(1), 3);
        passenger8.makeReservation(flights.get(0), 4);
        passenger9.makeReservation(flights.get(1), 5);


        Flight flightF101 = flightData.getFlightByCode("F101");
        System.out.println("Reservations for Flight F101:");
        for (int day = 1; day <= 7; day++) {
            int passengerCount = flightF101.getPassengerCountForDay(day);
            System.out.println("Day " + day + ": " + passengerCount + " passengers.");
        }

        Flight flightF102 = flightData.getFlightByCode("F102");
        System.out.println("Reservations for Flight F102:");
        for (int day = 1; day <= 7; day++) {
            int passengerCount = flightF102.getPassengerCountForDay(day);
            System.out.println("Day " + day + ": " + passengerCount + " passengers.");
        }

        Flight flightF103 = flightData.getFlightByCode("F103");
        System.out.println("Reservations for Flight F103:");
        for (int day = 1; day <= 7; day++) {
            int passengerCount = flightF103.getPassengerCountForDay(day);
            System.out.println("Day " + day + ": " + passengerCount + " passengers.");
        }


    }
}