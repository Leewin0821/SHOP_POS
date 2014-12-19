/*
 * Created by IntelliJ IDEA.
 * User: lwzhang
 * Date: 12/19/14
 * Time: 9:34 AM
 */
package ShopPos;

import Promotions.DiscountPromotion;
import Promotions.Promotion;
import Promotions.ReductionPromotion;
import Promotions.SecondHalfPromotion;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;

public class ShoppingPromotionModule extends AbstractModule {
    Binder binder;
    public void configure() {
//add configuration logic here
        binder.bind(Promotion.class).to(DiscountPromotion.class);
        binder.bind(Promotion.class).to(ReductionPromotion.class);
        binder.bind(Promotion.class).to(SecondHalfPromotion.class);
    }
}
