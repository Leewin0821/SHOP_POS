package Parsers;

import ShopPos.Good;

/**
 * Created by leewin on 14/11/28.
 */
public class ItemListParser extends Parser {

    @Override
    protected Good convert(String input) {
        String[] groups = input.split(":");
        String name = groups[0];
        double price = Double.valueOf(groups[1]);
        return new Good(name, price);
    }
}
