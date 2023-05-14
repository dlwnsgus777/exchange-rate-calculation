package youth.web.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import youth.exchange.application.ExchangeService;
import youth.exchange.dto.GetExchangeRatesResponse;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final ExchangeService exchangeService;

    @GetMapping("/")
    public String index(Model model) {
        GetExchangeRatesResponse rates = exchangeService.getExchangeRates();
        model.addAttribute("exchangeRates", rates.getExchangeRates());
        return "exchange-rate-calculation";
    }
}
