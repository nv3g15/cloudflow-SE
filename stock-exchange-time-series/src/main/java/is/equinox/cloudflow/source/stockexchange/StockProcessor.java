package is.equinox.cloudflow.source.stockexchange;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class StockProcessor {

    DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");

    String[] formatStocks() throws IOException  {
        StockController controller = new StockController();
        String tmpRaw = controller.generateStocks();

        try {
            return parse(tmpRaw);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    String[] formatStocks(String function, String symbol, String api) throws IOException {
        StockController controller = new StockController();
        String tmpRaw = controller.generateStocks(function, symbol, api);

        try {
            return parse(tmpRaw);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    String[] parse(String responseBody) throws JSONException {
        df.setTimeZone(TimeZone.getTimeZone("IST"));
        if(!responseBody.startsWith("[")) {
            responseBody = responseBody.substring(responseBody.indexOf("["), responseBody.indexOf("]") + 1);
        }
        JSONArray stocks = new JSONArray(responseBody);
        String[] stockArr = new String[stocks.length()];
        if(stocks.length()==0) {
            return null;
        } else {
            for (int i = 0; i < stocks.length(); i++) {
                JSONObject stock = stocks.getJSONObject(i);
                String date = stock.getString("date");
                double close = stock.getDouble("close");

                if(date.contains(":")) {
                    Timestamp time = Timestamp.valueOf(date);
                    stockArr[i] = df.format(time) + ", " + close;
                } else {
                    Date time = Date.valueOf(date);
                    stockArr[i] = df.format(time) + ", " + close;
                }
            }
            return stockArr;
        }
    }

    void write (String[]x) throws IOException{
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("data.csv"));
        for (String s : x) {
            outputWriter.write(s);
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }
}


