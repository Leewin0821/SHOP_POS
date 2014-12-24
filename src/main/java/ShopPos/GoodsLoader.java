package ShopPos;

import Parsers.ItemListParser;
import Parsers.Parser;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lwzhang on 12/23/14.
 */
public class GoodsLoader {

    private static final String ITEMLIST_FILE_PATH = "itemlist.txt";
    Injector injector = Guice.createInjector();
    Parser itemListParser = injector.getInstance(ItemListParser.class);
    GoodsShelf goodsShelf = GoodsShelf.getInstance();
    public GoodsLoader() {
    }

    public void loadGoods() throws IOException {
        ArrayList<Good> itemList = itemListParser.parse(ITEMLIST_FILE_PATH);
        for (Good good : itemList) {
            goodsShelf.addItemToShelf(new ShoppingItem(good, 0));
        }
    }
}
