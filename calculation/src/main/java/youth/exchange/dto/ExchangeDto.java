package youth.exchange.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import youth.exchange.domain.Exchange;

@Getter(value = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ExchangeDto {
  private String code;
  private Double exchangeRate;

  public Exchange toEntity() {
    return new Exchange(null, code, exchangeRate);
  }
}
