package Parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by leewin on 14/11/28.
 */
public class CartParser extends Parser {

    @Override
    protected Map.Entry<String, Integer> convert(String input) {
        String name = null;
        int quantity = 0;
        if (input.contains("-")) {
            String[] groups = input.split("-");
            name = groups[0];
            quantity = Integer.valueOf(groups[1]);
        } else {
            name = input;
            quantity = 1;
        }
        Map<String, Integer> cartItemsMap = new HashMap<String, Integer>();
        cartItemsMap.put(name, quantity);
        Set<Map.Entry<String, Integer>> cartItemsSet = cartItemsMap.entrySet();
        return cartItemsSet.iterator().next();
    }
}
