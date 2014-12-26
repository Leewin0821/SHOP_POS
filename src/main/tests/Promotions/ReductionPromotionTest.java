package Promotions;

import ShopPos.Good;
import ShopPos.ShoppingItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ReductionPromotionTest {

    private ReductionPromotion reductionPromotion;

    @Before
    public void setUp() throws Exception {
        reductionPromotion = new ReductionPromotion();
    }

    @After
    public void tearDown() throws Exception {
        reductionPromotion = null;
    }

    @Test
    public void should_get_correct_sale_amount_when_given_item_price_more_than_100() throws Exception {
        ShoppingItem shoppingItem = new ShoppingItem(new Good("ItemTest", 101), 1);
        shoppingItem.setPromotion(reductionPromotion);

        reductionPromotion.doPromote(shoppingItem);

        assertTrue(shoppingItem.getSaleAmount() == 96);
    }

    @Test
    public void should_get_correct_sale_amount_when_given_item_price_less_than_100() throws Exception {
        ShoppingItem shoppingItem = new ShoppingItem(new Good("ItemTest", 99), 1);
        shoppingItem.setPromotion(reductionPromotion);

        reductionPromotion.doPromote(shoppingItem);

        assertTrue(shoppingItem.getSaleAmount() == 99);
    }

    @Test
    public void should_get_correct_sale_amount_when_given_item_with_multiple_quantity() throws Exception {
        ShoppingItem shoppingItem = new ShoppingItem(new Good("ItemTest", 101), 3);
        shoppingItem.setPromotion(reductionPromotion);

        reductionPromotion.doPromote(shoppingItem);

        assertTrue(shoppingItem.getSaleAmount() == 288);
    }
}