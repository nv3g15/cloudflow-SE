package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

public class Interface {
    // https://www.alphavantage.co/documentation/
    public static String function = "TIME_SERIES_DAILY";
    public static String symbol = "TSLA";
    public static String interval = null;
    public static String api = "A5K060RWV2X3BVP5";

    public static String generateStocks() throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(
                "https://www.alphavantage.co/query?function=" + function +
                        "&symbol=" + symbol +
                        "&interval=" + interval +
                        "&apikey=" + api));

        return request.execute().parseAsString();
    }

    public static void setParams(String function, String symbol, String api) {
        Interface.function = function;
        Interface.symbol = symbol;
        Interface.api = api;
    }
}

