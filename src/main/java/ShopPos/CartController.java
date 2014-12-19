package ShopPos;

import Parsers.*;
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
 * Created by leewin on 14/11/28.
 */
public class CartController {
    private static final String CART_FILE_PATH = "cart.txt";
    private static final String DISCOUNT_PROMOTION_FILE_PATH = "discount_promotion.txt";
    private static final String ITEMLIST_FILE_PATH = "itemlist.txt";
    private static final String REDUCTIONLIST_PROMOTION_FILE_PATH = "reductionlist.txt";
    private static final String SECOND_HALF_PROMOTION_FILE_PATH = "second_half_price_promotion.txt";

    Injector injector = Guice.createInjector();
    Promotion discountPromotion = injector.getInstance(DiscountPromotion.class);
    Promotion secondHalfPromotion = injector.getInstance(SecondHalfPromotion.class);
    Promotion reductionPromotion = injector.getInstance(ReductionPromotion.class);
    Parser reductionParser = injector.getInstance(ReductionParser.class);
    Parser itemListParser = injector.getInstance(ItemListParser.class);
    Parser discountParser = injector.getInstance(DiscountParser.class);
    Parser cartParser = injector.getInstance(CartParser.class);
    Parser secondHalfParser = injector.getInstance(SecondHalfParser.class);

    private ArrayList<ShoppingItem> shoppingItems = new ArrayList<ShoppingItem>();
    private Cart cart;

    public CartController(Cart cart) {
        this.cart = cart;
    }

    public void purchase() throws IOException {

        readFromItemListFile();

        readFromCartFile();

        cart.addShoppingItemList(shoppingItems);

        readFromDiscountPromotionFile();

        readFromSecondHalfPromotionFile();

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
            setDiscountPromotionToShoppingItem(itemName , discountRate);
        }
    }

    private void setDiscountPromotionToShoppingItem(String itemName , Integer discountRate) {
        for (ShoppingItem shoppingItem : shoppingItems) {
            if (itemName.equals(shoppingItem.getGood().getName())) {
                shoppingItem.setPromotion(discountPromotion);
                shoppingItem.setDiscountRate(discountRate);
            }
        }
    }

    private void readFromCartFile() throws IOException {
        ArrayList<Map.Entry<String, Integer>> cartList = cartParser.parse(CART_FILE_PATH);
        for (Map.Entry<String, Integer> entry : cartList) {
            String name = entry.getKey();
            int quantity = entry.getValue();
            addShoppingItemFromCart(name, quantity);
        }
    }

    private void addShoppingItemFromCart(String name, int quantity) {
        for (ShoppingItem shoppingItem : shoppingItems) {
            if (name.equals(shoppingItem.getGood().getName())) {
                int totalQuantity = shoppingItem.getQuantity();
                totalQuantity += quantity;
                shoppingItem.setQuantity(totalQuantity);
                shoppingItem.setInitialSaleAmount();
            }
        }
    }

    private void readFromItemListFile() throws IOException {
        ArrayList<Good> itemList = itemListParser.parse(ITEMLIST_FILE_PATH);
        for (Good good : itemList) {
            shoppingItems.add(new ShoppingItem(good, 0));
        }
    }

}
