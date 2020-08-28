package is.equinox.cloudflow.source.stockexchange;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StockExchangeSourceStockAppEntryTest {

    private final StockController controller = new StockController();

    @Test
    void badParameter_Test() throws IOException {
        assertNull(controller.generateStocks("historical-chart/1min/","TSLAr?", "d516597aa6499ad22b6c24d8ae06686d"));
    }

    @Test
    void goodParameter_Test() throws IOException {
        assertNotNull(controller.generateStocks("historical-chart/1min/","TSLA?", "d516597aa6499ad22b6c24d8ae06686d"));
    }

    @Test
    void goodParameter_readTest() throws IOException {
        StockReadProperties.getPropValues();
        assertNotNull(controller.generateStocks());
    }
}

