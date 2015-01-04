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
import java.util.List;
import java.util.Map;

import static com.sun.tools.javac.util.List.from;

/**
 * Created by lwzhang on 12/24/14.
 */
public class Cashier {
    private static final String DISCOUNT_PROMOTION_FILE_NAME = "discount_promotion.txt";
    private static final String REDUCTIONLIST_PROMOTION_FILE_NAME = "reductionlist.txt";
    private static final String SECOND_HALF_PROMOTION_FILE_NAME = "second_half_price_promotion.txt";

    private Parser reductionParser;
    private Parser secondHalfParser;
    private Parser discountParser;

    Injector injector = Guice.createInjector();
    Promotion discountPromotion = injector.getInstance(DiscountPromotion.class);
    Promotion reductionPromotion = injector.getInstance(ReductionPromotion.class);
    Promotion secondHalfPromotion = injector.getInstance(SecondHalfPromotion.class);

    public Cashier()
    {
        setReductionParser(injector.getInstance(ReductionParser.class));
        setSecondHalfParser(injector.getInstance(SecondHalfParser.class));
        setDiscountParser(injector.getInstance(DiscountParser.class));
    }

    private Cart cart;
    private List<ShoppingItem> shoppingItems;

    public void checkOut() throws IOException {
        shoppingItems = cart.getShoppingItemList();
        readSecondHalfPromotionFromTextFile(SECOND_HALF_PROMOTION_FILE_NAME);
        readDiscountPromotionFromTextFile(DISCOUNT_PROMOTION_FILE_NAME);
        readReductionPromotionFromTextFile(REDUCTIONLIST_PROMOTION_FILE_NAME);
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

    private void readReductionPromotionFromTextFile(String fileName) throws IOException {
        ArrayList<String> reductionList = reductionParser.parse(fileName);
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

    private void readSecondHalfPromotionFromTextFile(String fileName) throws IOException {
        ArrayList<String> secondHalfList = secondHalfParser.parse(fileName);
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

    private void readDiscountPromotionFromTextFile(String fileName) throws IOException {
        ArrayList<Map.Entry<String, Integer>> discountItemList =
                discountParser.parse(fileName);
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

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setReductionParser(Parser reductionParser) {
        this.reductionParser = reductionParser;
    }

    public void setSecondHalfParser(Parser secondHalfParser) {
        this.secondHalfParser = secondHalfParser;
    }

    public void setDiscountParser(Parser discountParser) {
        this.discountParser = discountParser;
    }
}
