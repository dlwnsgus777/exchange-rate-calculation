package youth.exchange.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import youth.exchange.application.dto.CalcReceivedAmountRequest;
import youth.exchange.application.dto.CalcReceivedAmountResponse;
import youth.exchange.domain.Exchange;
import youth.exchange.infrastructure.ExchangeRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class})
class ExchangeServiceTest {
    @InjectMocks
    ExchangeService exchangeService;
    @Mock
    ExchangeRepository exchangeRepository;

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

}

