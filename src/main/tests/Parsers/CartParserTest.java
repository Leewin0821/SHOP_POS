package Parsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class CartParserTest {
    private CartParser cartParser;

    @Before
    public void setUp() throws Exception {
        cartParser = new CartParser();
    }

    @After
    public void tearDown() throws Exception {
        cartParser = null;
    }

    @Test
    public void should_get_one_shoppingItem_entry_when_given_one_valid_data_line() throws Exception {
        String inputLine = "ITEM000001";

        Map.Entry<String, Integer> convertEntry = cartParser.convert(inputLine);

        assertThat(convertEntry.getKey(), equalTo("ITEM000001"));
        assertThat(convertEntry.getValue(), is(1));
    }

    @Test
    public void should_get_correct_shoppingItem_quality_when_given_item_with_multiple_quality() throws Exception {
        String inputLine = "ITEM000001-2";

        Map.Entry<String, Integer> convertEntry = cartParser.convert(inputLine);

        assertThat(convertEntry.getKey(), equalTo("ITEM000001"));
        assertThat(convertEntry.getValue(), is(2));
    }

    @Test
    public void should_get_multiple_shoppingItem_entry_when_given_multiple_different_item_valid_data_lines() throws Exception {
        String inputLine = "ITEM000005-2";
        HashMap<String, Integer> cartItemsMap = new HashMap<String, Integer>();
        cartItemsMap.put("ITEM000001", 1);

        Map.Entry<String, Integer> convertEntry = cartParser.convert(inputLine);
        String convertEntryKey = convertEntry.getKey();
        Integer convertEntryValue = convertEntry.getValue();
        cartItemsMap.put(convertEntryKey, convertEntryValue);

        assertThat(cartItemsMap.containsKey("ITEM000005"), is(true));
        assertThat(cartItemsMap.containsValue(2), is(true));
    }

}