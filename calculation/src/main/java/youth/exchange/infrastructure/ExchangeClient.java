package youth.exchange.infrastructure;

import youth.exchange.dto.ExchangeDto;

import java.util.List;

public interface ExchangeClient {

    List<ExchangeDto> getExchangeRates();
}
