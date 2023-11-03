package domainModel;

public class Aircraft {
    private String code;
    private String model;
    private int totalSeats;

    public Aircraft(String code, String model, int totalSeats) {
        this.code = code;
        this.model = model;
        this.totalSeats = totalSeats;
    }

    public String getCode() {
        return code;
    }

    public String getModel() {
        return model;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

}

