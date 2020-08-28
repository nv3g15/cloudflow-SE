package is.equinox.cloudflow.source.stockexchange;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class StockProcessor {

    DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");

    String parse(String responseBody) throws JSONException {
        df.setTimeZone(TimeZone.getTimeZone("IST"));
        if(!responseBody.startsWith("[")) {
            responseBody = responseBody.substring(responseBody.indexOf("["), responseBody.indexOf("]") + 1);
        }
        JSONArray stocks = new JSONArray(responseBody);
        String stockArr;
        if(stocks.length()==0) {
            return null;
        } else {
            JSONObject stock = stocks.getJSONObject(0);
            String date = stock.getString("date");
            double close = stock.getDouble("close");

            if (date.contains(":")) {
                Timestamp time = Timestamp.valueOf(date);
                stockArr = df.format(time) + ", " + close;
            } else {
                Date time = Date.valueOf(date);
                stockArr = df.format(time) + ", " + close;
            }
            return stockArr;
        }
    }
}
