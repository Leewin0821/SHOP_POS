package Promotions;

import ShopPos.ShoppingItem;

/**
 * Created by leewin on 14/11/28.
 */
public class ReductionPromotion extends Promotion {
    private double reductionAmount = 5;

    @Override
    protected void doPromote(ShoppingItem shoppingItem) {
        if (shoppingItem.hasPromotion(this)) {
            double amount = shoppingItem.getSaleAmount();
            double remainder = amount % 100;
            double multiple = (amount - remainder) / 100;
            shoppingItem.setSaleAmount(amount - multiple * reductionAmount);
        }
    }
}
