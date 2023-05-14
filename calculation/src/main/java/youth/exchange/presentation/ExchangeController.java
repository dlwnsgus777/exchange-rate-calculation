package youth.exchange.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youth.exchange.application.ExchangeService;
import youth.exchange.application.dto.CalcReceivedAmountRequest;
import youth.exchange.application.dto.CalcReceivedAmountResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping("/exchange-rates/calculation")
    public CalcReceivedAmountResponse calcReceivedAmount(CalcReceivedAmountRequest dto) {
        return exchangeService.calcReceivedAmount(dto);
    }
}
