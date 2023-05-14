package youth.exchange.application;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import youth.exchange.dto.CalcReceivedAmountRequest;
import youth.exchange.dto.CalcReceivedAmountResponse;
import youth.exchange.domain.Exchange;
import youth.exchange.dto.ExchangeDto;
import youth.exchange.infrastructure.ExchangeClient;
import youth.exchange.infrastructure.ExchangeRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final ExchangeClient exchangeClient;

    @PostConstruct
    public void initExchange() {
        this.insertExchangeRates();
    }


    public CalcReceivedAmountResponse calcReceivedAmount(CalcReceivedAmountRequest dto) {
        Optional<Exchange> findExchange = exchangeRepository.getByCode(dto.getCode());
        Double calcResult = dto.getMoney() * findExchange.get().exchangeRate();

        return new CalcReceivedAmountResponse(calcResult);
    }

    @Scheduled(cron = "0 0 0/6 * * *")
    public void insertExchangeRates() {
        List<ExchangeDto> resultExchangeRates = exchangeClient.getExchangeRates();
        for (ExchangeDto resultExchangeRate : resultExchangeRates) {
            exchangeRepository.save(resultExchangeRate.toEntity());
        }
    }
}
