package Promotions;

import ShopPos.Good;
import ShopPos.ShoppingItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SecondHalfPromotionTest {

    private SecondHalfPromotion secondHalfPromotion;

    @Before
    public void setUp() throws Exception {
        secondHalfPromotion = new SecondHalfPromotion();
    }

    @After
    public void tearDown() throws Exception {
        secondHalfPromotion = null;
    }

    @Test
    public void should_get_correct_sale_amount_when_given_even_quantity_items() throws Exception {
        ShoppingItem shoppingItem = new ShoppingItem(new Good("ItemTest", 100), 2);
        shoppingItem.setPromotion(secondHalfPromotion);

        secondHalfPromotion.doPromote(shoppingItem);

        assertTrue(shoppingItem.getSaleAmount() == 150);
    }

    @Test
    public void should_get_correct_sale_amount_when_given_odd_quantity_items() throws Exception {
        ShoppingItem shoppingItem = new ShoppingItem(new Good("ItemTest", 100), 3);
        shoppingItem.setPromotion(secondHalfPromotion);

        secondHalfPromotion.doPromote(shoppingItem);

        assertTrue(shoppingItem.getSaleAmount() == 250);
    }


}