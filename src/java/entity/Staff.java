package entity;

public class Staff {
    private int id;
    private int role_id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;

    public Staff(int id, int role_id, String username, String password, String fullName, String email, String phone) {
        this.id = id;
        this.role_id = role_id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", role_id=" + role_id + ", username=" + username + ", password=" + password + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone + '}';
    }
}
