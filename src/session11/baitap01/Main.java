package session11.baitap01;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== DANH SÁCH BỆNH NHÂN ===");

        PatientDao dao = new PatientDao();
        dao.getPatients();
    }
}
