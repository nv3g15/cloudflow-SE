package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

public class StockController {

    String generateStocks() throws IOException {

        ReadProperties prop = new ReadProperties();
        prop.getPropValues();

        String function = ReadProperties.function;
        String symbol = ReadProperties.symbol;
        String api = ReadProperties.api;

        String stockURL = "https://financialmodelingprep.com/api/v3/" +
                function +
                symbol +
                "apikey=" + api;

        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(stockURL));
        return request.execute().parseAsString();
    }

    String generateStocks(String function, String symbol, String api) throws IOException {

        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        String stockURL = "https://financialmodelingprep.com/api/v3/" +
                function +
                symbol +
                "apikey=" + api;

        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(stockURL));
        return request.execute().parseAsString();
    }
}
