package session06.demo;

public class Datashared {
    private int number;
    private int control = 1;

    public Datashared() {
    }

    public Datashared(int number, int control) {
        this.number = number;
        this.control = control;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }
}
