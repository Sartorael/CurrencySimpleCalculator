package solution.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import solution.service.validator.Currency;


import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Validated
@Service
public class ConverterService {
    public String currencyApiCom(@Currency String baseCurrency,@Currency String targetCurrency,@NotNull double amount){
        StringBuilder response = new StringBuilder();
        String answerValue = "";
        String apiKey = "cur_live_30s349IKIMmrWrC3ZcOX49JIycCjXfMJUx2tHVq4";
        String route = "https://api.currencyapi.com/v3/latest?apikey="+apiKey;
        try {
            URL url = new URL("https://api.currencyapi.com/v3/latest?apikey="+apiKey+"&base_currency="+baseCurrency+"&currencies="+targetCurrency);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);;
            }
            reader.close();
            request.disconnect();
            String [] parts = response.toString().split("\"value\":");
            double moment = Double.parseDouble(parts[1].trim().replaceAll("}", ""));
            double result = moment * amount;
             answerValue = String.format("%.2f", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answerValue;
    }
}
