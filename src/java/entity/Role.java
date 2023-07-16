package entity;

public class Role {
    private int roleId;
    private String title;

    public Role() {
    }

    public Role(int roleId, String title) {
        this.roleId = roleId;
        this.title = title;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
