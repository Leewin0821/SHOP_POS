package ShopPos;

import java.io.IOException;

/**
 * Created by leewin on 14/11/27.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        GoodsLoader goodsLoader = new GoodsLoader();
        goodsLoader.loadGoods();

        Cart cart = new Cart();

        Customer customer = new Customer(cart);
        customer.purchase();

        Cashier cashier = new Cashier(cart);
        cashier.checkOut();
    }
}
