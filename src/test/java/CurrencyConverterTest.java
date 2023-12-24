import org.junit.jupiter.api.Test;
import solution.service.ConverterService;

class CurrencyConverterTest {

    @Test
    void testEuBakApi() {
        String baseCurrency = "USD";
        String targetCurrency = "RUB";
        double amount = 100.0;
        ConverterService cc = new ConverterService();
        cc.currencyApiCom(baseCurrency, targetCurrency, amount);
    }
}