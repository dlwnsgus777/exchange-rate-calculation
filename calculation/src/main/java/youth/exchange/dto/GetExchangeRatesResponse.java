package youth.exchange.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import youth.exchange.domain.Exchange;

import java.util.List;
import java.util.stream.Collectors;

@Getter(value = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class GetExchangeRatesResponse {
  private List<ExchangeDto> exchangeRates;

  public static GetExchangeRatesResponse from(List<Exchange> entities) {
    return new GetExchangeRatesResponse(entities.stream().map(ExchangeDto::from).collect(Collectors.toList()));
  }
}
