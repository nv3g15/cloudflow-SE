package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

public class StockController {

    String generateStocks() throws IOException {

        StockReadProperties prop = new StockReadProperties();
        prop.getPropValues();

        String function = StockReadProperties.function;
        String symbol = StockReadProperties.symbol;
        String api = StockReadProperties.api;

        String stockURL = "https://financialmodelingprep.com/api/v3/" +
                function +
                symbol +
                "apikey=" + api;

        StockProcessor processor = new StockProcessor();
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(stockURL));
        return processor.parse(request.execute().parseAsString());
    }

    public String generateStocks(String function, String symbol, String api) throws IOException {

        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        String stockURL = "https://financialmodelingprep.com/api/v3/" +
                function +
                symbol +
                "apikey=" + api;

        StockProcessor processor = new StockProcessor();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(stockURL));
        return processor.parse(request.execute().parseAsString());
    }
}
