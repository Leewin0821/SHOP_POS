package Parsers;

import ShopPos.Good;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ItemListParserTest {
    private ItemListParser itemListParser;

    @Before
    public void setUp() throws Exception {
        itemListParser = new ItemListParser();
    }

    @After
    public void tearDown() throws Exception {
        itemListParser = null;
    }

    @Test
    public void should_get_correct_good_when_given_valid_data_line() throws Exception {
        String inputLine = "ITEM000001:40";

        Good convertedGood = itemListParser.convert(inputLine);

        assertThat(convertedGood.getName(), is("ITEM000001"));
        assertThat(convertedGood.getPrice(), is(40.0));
    }
}