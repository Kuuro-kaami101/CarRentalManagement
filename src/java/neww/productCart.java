package neww;

public class productCart {
    int cartid, pid, amount;

    public productCart(int cartid, int pid, int amount) {
        this.cartid = cartid;
        this.pid = pid;
        this.amount = amount;
    }

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "productCart{" + "cartid=" + cartid + ", pid=" + pid + ", amount=" + amount + '}';
    }
}
