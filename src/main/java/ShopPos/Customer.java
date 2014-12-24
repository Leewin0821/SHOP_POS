package ShopPos;

import Parsers.CartParser;
import Parsers.Parser;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by leewin on 14/11/28.
 */
public class Customer {
    private static final String CART_FILE_PATH = "cart.txt";

    Injector injector = Guice.createInjector();
    Parser cartParser = injector.getInstance(CartParser.class);

    private Cart cart;
    private GoodsShelf goodsShelf = GoodsShelf.getInstance();
    private ArrayList<ShoppingItem> shoppingItems = goodsShelf.getShoppingItems();


    public void purchase() throws IOException {

        readFromCartFile();
        cart.addShoppingItemList(shoppingItems);
    }

    private void readFromCartFile() throws IOException {
        ArrayList<Map.Entry<String, Integer>> cartList = cartParser.parse(CART_FILE_PATH);
        for (Map.Entry<String, Integer> entry : cartList) {
            String name = entry.getKey();
            int quantity = entry.getValue();
            addShoppingItemFromCart(name, quantity);
        }
    }

    private void addShoppingItemFromCart(String name, int quantity) {
        for (ShoppingItem shoppingItem : shoppingItems) {
            if (name.equals(shoppingItem.getGood().getName())) {
                int totalQuantity = shoppingItem.getQuantity();
                totalQuantity += quantity;
                shoppingItem.setQuantity(totalQuantity);
                shoppingItem.setInitialSaleAmount();
            }
        }
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
