package Promotions;

import ShopPos.ShoppingItem;

/**
 * Created by leewin on 14/11/28.
 */
public class DiscountPromotion extends Promotion {

    @Override
    protected void doPromote(ShoppingItem shoppingItem) {
        if (shoppingItem.hasPromotion(this)) {
            double amount = shoppingItem.getSaleAmount() * shoppingItem.getDiscountRate() / 100;
            shoppingItem.setSaleAmount(amount);
        }
    }
}
