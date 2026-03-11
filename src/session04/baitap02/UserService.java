package session04.baitap02;

public class UserService {
    public static boolean checkRegistrationAge(int age){
        if(age < 0){
            throw new IllegalArgumentException("tuổi không được âm");
        }
        return age >= 18;
    }
}
