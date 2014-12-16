package Promotions;

import ShopPos.Cart;
import ShopPos.ShoppingItem;

import java.util.ArrayList;

/**
 * Created by leewin on 14/11/28.
 */
public abstract class Promotion {
    protected Promotion successor = null;

    public Promotion getSuccessor() {
        return successor;
    }

    public void setSuccessor(Promotion successor) {
        this.successor = successor;
    }

    public void getAmount(Cart cart) {
        ArrayList<ShoppingItem> shoppingItems = cart.getShoppingItemList();
        for (ShoppingItem shoppingItem : shoppingItems) {
            doPromote(shoppingItem);
        }
        if (getSuccessor() != null) {
            getSuccessor().getAmount(cart);
        }
    }

    protected abstract void doPromote(ShoppingItem shoppingItem);
}
