package session11.baitap04;

public class Main {

    public static void main(String[] args) {

        PatientDao dao = new PatientDao();
        dao.searchPatient("Nguyen Van A");

        dao.searchPatient("' OR '1'='1");
    }
}
// phan 1
//Do nối chuỗi SQL → bị SQL Injection
//' OR '1'='1 làm WHERE luôn TRUE
//→ trả toàn bộ dữ liệu