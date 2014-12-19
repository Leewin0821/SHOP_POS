/*
 * Created by IntelliJ IDEA.
 * User: leewin
 * Date: 14/12/20
 * Time: 01:26
 */
package ShopPos;

import Parsers.*;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;

public class ShoppingParserModule extends AbstractModule {
    Binder binder;

    protected void configure() {
//add configuration logic here
        binder.bind(Parser.class).to(DiscountParser.class);
        binder.bind(Parser.class).to(ItemListParser.class);
        binder.bind(Parser.class).to(ReductionParser.class);
        binder.bind(Parser.class).to(SecondHalfParser.class);
    }
}
