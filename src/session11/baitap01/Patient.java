package session11.baitap01;

public class Patient {
    private int id;
    private String name;
    private String disease;

    public Patient(int id, String name, String disease) {
        this.id = id;
        this.name = name;
        this.disease = disease;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDisease() { return disease; }
}