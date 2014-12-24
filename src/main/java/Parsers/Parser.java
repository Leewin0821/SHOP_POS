package Parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by leewin on 14/11/23.
 */
public abstract class Parser<T> {
    private ArrayList<T> valueList;

    public Parser() {
        valueList = new ArrayList<T>();
    }

    public ArrayList<T> parse(String path) throws IOException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String input = reader.readLine();
        while (input != null) {
            valueList.add(convert(input));
            input = reader.readLine();
        }
        inputStreamReader.close();
        return valueList;
    }

    protected abstract T convert(String input);
}
