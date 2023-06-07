package neww;

public class Cart {
    int cartId, id;

    public Cart(int cartId, int id) {
        this.cartId = cartId;
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cart{" + "cartId=" + cartId + ", id=" + id + '}';
    }   
}
