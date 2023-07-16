package validate;

public class Validate {
    public boolean checkEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
    
    public boolean checkPhone(String phone) {
        String regex = "^0\\d{9}$";
        return phone.matches(regex);
    }
}
