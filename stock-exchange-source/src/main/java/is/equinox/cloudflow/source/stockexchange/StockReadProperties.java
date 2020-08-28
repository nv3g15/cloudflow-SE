package is.equinox.cloudflow.source.stockexchange;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StockReadProperties {

    static String function;
    static String symbol;
    static String api;

    static void getPropValues() throws IOException {
        String propFileName = "config.properties";

        try (InputStream input = StockReadProperties.class.getClassLoader().getResourceAsStream(propFileName)) {
            Properties prop = new Properties();

            prop.load(input);

            function = prop.getProperty("function");
            symbol = prop.getProperty("symbol");
            api = prop.getProperty("api");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
