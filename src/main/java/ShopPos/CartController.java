package ShopPos;

import Parsers.*;
import Promotions.DiscountPromotion;
import Promotions.ReductionPromotion;
import Promotions.SecondHalfPromotion;

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

    DiscountPromotion discountPromotion = new DiscountPromotion();
    SecondHalfPromotion secondHalfPromotion = new SecondHalfPromotion();
    ReductionPromotion reductionPromotion = new ReductionPromotion();

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
        Parser reductionParser = new ReductionParser();
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
        Parser secondHalfParser = new SecondHalfParser();
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
        Parser discountParser = new DiscountParser();
        ArrayList<Map.Entry<String, Integer>> discountItemList =
                discountParser.parse(DISCOUNT_PROMOTION_FILE_PATH);
        for (Map.Entry<String, Integer> entry : discountItemList) {
            String name = entry.getKey();
            discountPromotion.addDiscountItemAndDiscountRate(entry);
            setDiscountPromotionToShoppingItem(name);
        }
    }

    private void setDiscountPromotionToShoppingItem(String name) {
        for (ShoppingItem shoppingItem : shoppingItems) {
            if (name.equals(shoppingItem.getGood().getName())) {
                shoppingItem.setPromotion(discountPromotion);
            }
        }
    }

    private void readFromCartFile() throws IOException {
        Parser cartParser = new CartParser();
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
        Parser itemListParser = new ItemListParser();
        ArrayList<Good> itemList = itemListParser.parse(ITEMLIST_FILE_PATH);
        for (Good good : itemList) {
            shoppingItems.add(new ShoppingItem(good, 0));
        }
    }

}
