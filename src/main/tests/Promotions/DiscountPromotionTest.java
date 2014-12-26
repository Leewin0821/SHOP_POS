package Promotions;

import ShopPos.Good;
import ShopPos.ShoppingItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DiscountPromotionTest {

    private DiscountPromotion discountPromotion;

    @Before
    public void setUp() throws Exception {
        discountPromotion = new DiscountPromotion();
    }

    @After
    public void tearDown() throws Exception {
        discountPromotion = null;
    }

    @Test
    public void should_get_correct_sale_amount_when_given_discount_promotion() throws Exception {

        Good good = new Good("ItemTest", 100.0);
        ShoppingItem shoppingItem = new ShoppingItem(good,1);
        shoppingItem.setPromotion(discountPromotion);
        shoppingItem.setDiscountRate(90);

        discountPromotion.doPromote(shoppingItem);

        assertTrue(shoppingItem.getSaleAmount() == 90.0);
    }
}