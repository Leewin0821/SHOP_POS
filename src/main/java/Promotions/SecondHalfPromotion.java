package Promotions;

import ShopPos.ShoppingItem;

/**
 * Created by leewin on 14/11/28.
 */
public class SecondHalfPromotion extends Promotion {

    @Override
    protected void doPromote(ShoppingItem shoppingItem) {
        if (shoppingItem.hasPromotion(this)) {
            int quantity = shoppingItem.getQuantity();
            double price = shoppingItem.getGood().getPrice();
            double amount = shoppingItem.getSaleAmount();
            if (quantity % 2 == 0) {
                shoppingItem.setSaleAmount(amount * 0.75);
            } else {
                shoppingItem.setSaleAmount(amount * 0.75 + price * 0.25);
            }
        }
    }
}
