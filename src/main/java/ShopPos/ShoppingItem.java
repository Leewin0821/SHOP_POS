package ShopPos;

import Promotions.Promotion;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by leewin on 14/11/28.
 */
public class ShoppingItem {
    private Good good;
    private int quantity;
    private Collection<Promotion> promotions;
    private double saleAmount;
    private Integer discountRate;

    public ShoppingItem(Good good, int quantity) {
        this.good = good;
        this.quantity = quantity;
        discountRate = 100;
        promotions = new ArrayList<Promotion>();
        setInitialSaleAmount();
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSaleAmountWithoutPromotion() {
        return good.getPrice() * quantity;
    }

    public void setInitialSaleAmount() {
        this.saleAmount = good.getPrice() * quantity;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }


    public Collection<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Collection<Promotion> promotions) {
        this.promotions = promotions;
    }

    public void setPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public boolean hasPromotion(Promotion promotion) {
        return promotions.contains(promotion);
    }

    public String toString() {
        return good.getName() + "   " + quantity + "   " +
                good.getPrice() + "   " + saleAmount + "\n";
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }
}
