package ShopPos;

import java.util.ArrayList;

/**
 * Created by leewin on 14/11/28.
 */
public class Cart {
    private static final double REDUCTIONAMOUNT = 3;
    private ArrayList<ShoppingItem> shoppingItemList;

    public Cart() {
        shoppingItemList = new ArrayList<ShoppingItem>();
    }

    public void addShoppingItem(ShoppingItem shoppingItem) {
        shoppingItemList.add(shoppingItem);
    }

    public void addShoppingItemList(ArrayList<ShoppingItem> shoppingItemList) {
        this.shoppingItemList = shoppingItemList;
    }

    public ArrayList<ShoppingItem> getShoppingItemList() {
        return shoppingItemList;
    }

    public double getTotalSaleAmountWithPromotion() {
        double totalSaleAmount = 0;
        for (ShoppingItem shoppingItem : shoppingItemList) {
            totalSaleAmount += shoppingItem.getSaleAmount();
        }
        double remainder = totalSaleAmount % 100;
        double multiple = (totalSaleAmount - remainder) / 100;
        return totalSaleAmount - multiple * REDUCTIONAMOUNT;
    }

    public double getTotalSaleAmountWithoutPromotion() {
        double totalSaleAmount = 0;
        for (ShoppingItem shoppingItem : shoppingItemList) {
            totalSaleAmount += shoppingItem.getSaleAmountWithoutPromotion();
        }
        return totalSaleAmount;
    }
}
