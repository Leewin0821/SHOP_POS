package ShopPos;

import java.io.IOException;

/**
 * Created by leewin on 14/11/27.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Cart cart = new Cart();
        CartController cartController = new CartController(cart);
        cartController.purchase();
    }
}
