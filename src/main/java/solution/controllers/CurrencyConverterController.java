package solution.controllers;

import freemarker.template.Template;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import solution.service.ConverterService;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CurrencyConverterController {
    ConverterService cc = new ConverterService();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("result", "");
        return "index";
    }

    @PostMapping("/")
    public String convertCurrency(@RequestParam("api") String api, @RequestParam("currency") String currency,
                                  @RequestParam("value") double value, @RequestParam("convertTo") String convertTo, Model model) {

        String convertedAmount = cc.currencyApiCom(currency, convertTo, value);
        Map<String, Object> root = new HashMap<>();
        root.put("result", convertedAmount);
        model.addAttribute("result", convertedAmount);
        Writer out = new OutputStreamWriter(System.out);
        return "index";
    }
}