package Parsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DiscountParserTest {
    private DiscountParser discountParser;

    @Before
    public void setUp() throws Exception {
        discountParser = new DiscountParser();
    }

    @After
    public void tearDown() throws Exception {
        discountParser = null;
    }

    @Test
    public void should_get_correct_discount_rate_when_given_valid_input_line() throws Exception {
        String inputLine = "ITEM000005:90";

        Map.Entry<String, Integer> convertedEntry = discountParser.convert(inputLine);

        assertThat(convertedEntry.getKey(), is("ITEM000005"));
        assertThat(convertedEntry.getValue(), is(90));
    }
}