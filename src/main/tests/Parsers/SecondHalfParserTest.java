package Parsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SecondHalfParserTest {

    private SecondHalfParser secondHalfParser;

    @Before
    public void setUp() throws Exception {
        secondHalfParser = new SecondHalfParser();
    }

    @After
    public void tearDown() throws Exception {
        secondHalfParser = null;
    }

    @Test
    public void should_get_correct_item_when_given_valid_input_line() throws Exception {
        String inputLine = "ITEM000001";

        String convert = secondHalfParser.convert(inputLine);

        assertThat(convert, is("ITEM000001"));
    }
}