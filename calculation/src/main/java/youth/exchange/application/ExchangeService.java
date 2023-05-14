package youth.exchange.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import youth.exchange.application.dto.CalcReceivedAmountRequest;
import youth.exchange.application.dto.CalcReceivedAmountResponse;
import youth.exchange.domain.Exchange;
import youth.exchange.infrastructure.ExchangeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private ExchangeRepository exchangeRepository;

    public ExchangeService(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    public CalcReceivedAmountResponse calcReceivedAmount(CalcReceivedAmountRequest dto) {
        Optional<Exchange> findExchange = exchangeRepository.getByCode(dto.getCode());
        Double calcResult = dto.getMoney() * findExchange.get().exchangeRate();

        return new CalcReceivedAmountResponse(calcResult);
    }
}
