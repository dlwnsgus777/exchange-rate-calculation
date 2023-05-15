package youth.exchange.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youth.exchange.application.ExchangeService;
import youth.exchange.dto.CalcReceivedAmountRequest;
import youth.exchange.dto.CalcReceivedAmountResponse;
import youth.exchange.dto.GetExchangeRatesResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    @PostMapping("/exchange-rates/calculation")
    public CalcReceivedAmountResponse calcReceivedAmount(@RequestBody @Valid CalcReceivedAmountRequest dto) {
        return exchangeService.calcReceivedAmount(dto);
    }

    @GetMapping("/exchange-rates")
    public GetExchangeRatesResponse getExchangeRates() {
        return exchangeService.getExchangeRates();
    }
}
