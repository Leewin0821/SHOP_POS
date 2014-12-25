package Parsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReductionParserTest {

    private ReductionParser reductionParser;

    @Before
    public void setUp() throws Exception {
        reductionParser = new ReductionParser();
    }

    @After
    public void tearDown() throws Exception {
        reductionParser = null;
    }

    @Test
    public void should_get_correct_reduction_rate_when_given_valid_input_line() throws Exception {
        String inputLine = "ITEM000001";

        String convertLine = reductionParser.convert(inputLine);

        assertThat(convertLine, is("ITEM000001"));

    }
}