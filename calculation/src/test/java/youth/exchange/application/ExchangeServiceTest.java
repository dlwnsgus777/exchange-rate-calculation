package youth.exchange.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import youth.exchange.dto.CalcReceivedAmountRequest;
import youth.exchange.dto.CalcReceivedAmountResponse;
import youth.exchange.domain.Exchange;
import youth.exchange.dto.ExchangeDto;
import youth.exchange.infrastructure.ExchangeClient;
import youth.exchange.infrastructure.ExchangeRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith({MockitoExtension.class})
class ExchangeServiceTest {
    @InjectMocks
    ExchangeService exchangeService;
    @Mock
    ExchangeRepository exchangeRepository;
    @Mock
    ExchangeClient exchangeClient;

    @Test
    @DisplayName("수취 금액 계산하기")
    public void testReceviedAountResponse() {
        // given
        CalcReceivedAmountRequest dto = new CalcReceivedAmountRequest("KWD", 1000L);

        // when
        // 송금금액 * 환율 => 수취금액
        Mockito.when(exchangeRepository.getByCode(dto.getCode())).thenReturn(Optional.of(new Exchange(1L, "USDKWD", 1_121.419945)));
        CalcReceivedAmountResponse result = exchangeService.calcReceivedAmount(dto);

        // then
        assertThat(result.getReceivedAmount()).isEqualTo(1_121_419.945);
    }

    @Test
    @DisplayName("api 호출로 가져온 환율 정보 DB 저장하기")
    public void testExchangeRatesInsertToDB() {
        // given
        // when
        Mockito.when(exchangeClient.getExchangeRates()).thenReturn(getExchangeRates());
        exchangeService.insertExchangeRates();

        // then
        Mockito.verify(exchangeRepository, Mockito.times(3)).save(any());
    }

    private static List<ExchangeDto> getExchangeRates() {
        return List.of(
                new ExchangeDto("KRW", 1311.123),
                new ExchangeDto("JPY", 1111.1111),
                new ExchangeDto("PHP", 1211.1211));
    }

}

