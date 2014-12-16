package Parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by leewin on 14/11/27.
 */
public class DiscountParser extends Parser {

    @Override
    protected Map.Entry<String, Integer> convert(String input) {
        String[] groups = input.split(":");
        String itemName = groups[0];
        Integer itemDiscount = Integer.valueOf(groups[1]);
        Map<String, Integer> discountItemMap = new HashMap<String, Integer>();
        discountItemMap.put(itemName, itemDiscount);
        Set<Map.Entry<String, Integer>> discountItemSet = discountItemMap.entrySet();
        return discountItemSet.iterator().next();
    }

}
