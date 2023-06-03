package neww;

public class Purchase {
    int id, cartid, bill, status;
    String address, phone;

    public Purchase(int id, int cartid, int bill, int status, String address, String phone) {
        this.id = id;
        this.cartid = cartid;
        this.bill = bill;
        this.status = status;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardid() {
        return cartid;
    }

    public void setCardid(int cardid) {
        this.cartid = cardid;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Purchase{" + "id=" + id + ", cardid=" + cartid + ", bill=" + bill + ", status=" + status + ", address=" + address + ", phone=" + phone + '}';
    }  
}
