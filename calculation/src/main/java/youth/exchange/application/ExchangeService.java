package youth.exchange.application;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youth.exchange.domain.Exchange;
import youth.exchange.dto.CalcReceivedAmountRequest;
import youth.exchange.dto.CalcReceivedAmountResponse;
import youth.exchange.dto.ExchangeDto;
import youth.exchange.dto.GetExchangeRatesResponse;
import youth.exchange.infrastructure.ExchangeClient;
import youth.exchange.infrastructure.ExchangeRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final ExchangeClient exchangeClient;

    @PostConstruct
    public void initExchange() {
        this.insertExchangeRates();
    }

    @Transactional(readOnly = true)
    public CalcReceivedAmountResponse calcReceivedAmount(CalcReceivedAmountRequest dto) {
        Exchange findExchange = exchangeRepository.findByCode(dto.getCode()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코드입니다."));
        Double calcResult = dto.getMoney() * findExchange.exchangeRate();

        return new CalcReceivedAmountResponse(calcResult);
    }

    @Scheduled(cron = "0 0 0/6 * * *")
    public void insertExchangeRates() {
        List<ExchangeDto> resultExchangeRates = exchangeClient.getExchangeRates();
        for (ExchangeDto resultExchangeRate : resultExchangeRates) {
            Optional<Exchange> findEntity = exchangeRepository.findByCode(resultExchangeRate.getCode());
            if (findEntity.isEmpty()) {
                exchangeRepository.save(resultExchangeRate.toEntity());
                continue;
            }

            findEntity.get().updateExchangeRate(resultExchangeRate.getExchangeRate());
        }
    }

    @Transactional(readOnly = true)
    public GetExchangeRatesResponse getExchangeRates() {
        List<Exchange> result = exchangeRepository.findAll();
        return GetExchangeRatesResponse.from(result);
    }
}
