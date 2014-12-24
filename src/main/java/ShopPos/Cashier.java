package ShopPos;

import Parsers.DiscountParser;
import Parsers.Parser;
import Parsers.ReductionParser;
import Parsers.SecondHalfParser;
import Promotions.DiscountPromotion;
import Promotions.Promotion;
import Promotions.ReductionPromotion;
import Promotions.SecondHalfPromotion;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by lwzhang on 12/24/14.
 */
public class Cashier {
    private static final String DISCOUNT_PROMOTION_FILE_PATH = "discount_promotion.txt";
    private static final String REDUCTIONLIST_PROMOTION_FILE_PATH = "reductionlist.txt";
    private static final String SECOND_HALF_PROMOTION_FILE_PATH = "second_half_price_promotion.txt";

    Injector injector = Guice.createInjector();
    Promotion discountPromotion = injector.getInstance(DiscountPromotion.class);
    Promotion secondHalfPromotion = injector.getInstance(SecondHalfPromotion.class);
    Promotion reductionPromotion = injector.getInstance(ReductionPromotion.class);

    Parser reductionParser = injector.getInstance(ReductionParser.class);
    Parser secondHalfParser = injector.getInstance(SecondHalfParser.class);
    Parser discountParser = injector.getInstance(DiscountParser.class);

    private Cart cart;
    private ArrayList<ShoppingItem> shoppingItems;

    public Cashier(Cart cart) {
        this.cart = cart;
        shoppingItems = cart.getShoppingItemList();
    }

    public void checkOut() throws IOException {
        readFromSecondHalfPromotionFile();
        readFromDiscountPromotionFile();
        readFromReductionListFile();
        assemblePromotionChain();


        System.out.println(shoppingItems);
        System.out.println(cart.getTotalSaleAmountWithoutPromotion());
        System.out.println(cart.getTotalSaleAmountWithPromotion());
    }


    private void assemblePromotionChain() {
        secondHalfPromotion.setSuccessor(discountPromotion);
        discountPromotion.setSuccessor(reductionPromotion);
        secondHalfPromotion.getAmount(cart);
    }

    private void readFromReductionListFile() throws IOException {
        ArrayList<String> reductionList = reductionParser.parse(REDUCTIONLIST_PROMOTION_FILE_PATH);
        for (String name : reductionList) {
            setReductionPromotionToShoppingItem(name);
        }
    }

    private void setReductionPromotionToShoppingItem(String name) {
        for (ShoppingItem shoppingItem : shoppingItems) {
            if (name.equals(shoppingItem.getGood().getName())) {
                shoppingItem.setPromotion(reductionPromotion);
            }
        }
    }

    private void readFromSecondHalfPromotionFile() throws IOException {
        ArrayList<String> secondHalfList = secondHalfParser.parse(SECOND_HALF_PROMOTION_FILE_PATH);
        for (String name : secondHalfList) {
            setSecondHalfPromotionToShoppingItem(name);
        }
    }

    private void setSecondHalfPromotionToShoppingItem(String name) {
        for (ShoppingItem shoppingItem : shoppingItems) {
            if (name.equals(shoppingItem.getGood().getName())) {
                shoppingItem.setPromotion(secondHalfPromotion);
            }
        }
    }

    private void readFromDiscountPromotionFile() throws IOException {
        ArrayList<Map.Entry<String, Integer>> discountItemList =
                discountParser.parse(DISCOUNT_PROMOTION_FILE_PATH);
        for (Map.Entry<String, Integer> entry : discountItemList) {
            String itemName = entry.getKey();
            Integer discountRate = entry.getValue();
            setDiscountPromotionToShoppingItem(itemName, discountRate);
        }
    }

    private void setDiscountPromotionToShoppingItem(String itemName, Integer discountRate) {
        for (ShoppingItem shoppingItem : shoppingItems) {
            if (itemName.equals(shoppingItem.getGood().getName())) {
                shoppingItem.setPromotion(discountPromotion);
                shoppingItem.setDiscountRate(discountRate);
            }
        }
    }
}
