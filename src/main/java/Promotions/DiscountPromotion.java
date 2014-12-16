package Promotions;

import ShopPos.ShoppingItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leewin on 14/11/28.
 */
public class DiscountPromotion extends Promotion {
    private Map<String, Integer> discountItemMap;

    public DiscountPromotion() {
        discountItemMap = new HashMap<String, Integer>();
    }

    public void addDiscountItemAndDiscountRate(Map.Entry<String, Integer> entry) {
        discountItemMap.put(entry.getKey(), entry.getValue());
    }

    @Override
    protected void doPromote(ShoppingItem shoppingItem) {
        if (shoppingItem.hasPromotion(this)) {
            for (Map.Entry<String, Integer> entry : discountItemMap.entrySet()) {
                String discountItemName = entry.getKey();
                String shoppingItemName = shoppingItem.getGood().getName();
                if (discountItemName.equals(shoppingItemName)) {
                    double amount = shoppingItem.getSaleAmount() * discountItemMap.get(discountItemName) / 100;
                    shoppingItem.setSaleAmount(amount);
                }
            }
        }
    }
}
