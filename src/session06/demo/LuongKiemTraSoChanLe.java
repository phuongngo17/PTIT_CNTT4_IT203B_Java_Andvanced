package session06.demo;

public class LuongKiemTraSoChanLe {
    private Datashared datashared;
    public LuongKiemTraSoChanLe(Datashared datashared) {
        this.datashared = datashared;
    }
    public void run() {
        while(true){
           synchronized (datashared) {

           }
        }
    }
}
