package ShopPos;

import Parsers.SecondHalfParser;
import Promotions.SecondHalfPromotion;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CashierTest {

    private Cashier cashier;

    @Before
    public void setUp() throws Exception {
        cashier = new Cashier();
    }

    @After
    public void tearDown() throws Exception {
        cashier = null;
    }

    @Test
    public void shouldSetSecondHalfPromotionProperlyWhenGivenSecondHalfPromotionFile() throws IOException {
        // given
        SecondHalfParser secondHalfParser = mock(SecondHalfParser.class);
        String secondHalfGoodName = "aaaa";
        ArrayList<String> secondHalfGoodsList = Lists.newArrayList(secondHalfGoodName);
        given(secondHalfParser.parse(Mockito.anyString())).willReturn(secondHalfGoodsList);

        Good good = mock(Good.class);
        given(good.getName()).willReturn(secondHalfGoodName);
        ShoppingItem shoppingItem = new ShoppingItem(good, 1);
        Cart cart = mock(Cart.class);
        given(cart.getShoppingItemList()).willReturn(Lists.newArrayList(shoppingItem));
        cashier.setCart(cart);

        // when
        cashier.checkOut();

        // then
        assertTrue(shoppingItem.hasPromotion(new SecondHalfPromotion()));
    }
}