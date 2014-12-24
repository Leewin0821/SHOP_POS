package ShopPos;

import java.util.ArrayList;

/**
 * Created by lwzhang on 12/24/14.
 */
public class GoodsShelf {
    private static GoodsShelf goodShelfInstance = new GoodsShelf();
    private ArrayList<ShoppingItem> shoppingItems = new ArrayList<ShoppingItem>();

    private GoodsShelf() {
    }

    public static GoodsShelf getInstance() {
        return goodShelfInstance;
    }

    public void addItemToShelf(ShoppingItem shoppingItem) {
        shoppingItems.add(shoppingItem);
    }

    public ArrayList<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }
}
