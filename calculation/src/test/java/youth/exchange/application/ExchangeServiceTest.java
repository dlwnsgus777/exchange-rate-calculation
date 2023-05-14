package youth.exchange.application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("환율 관련 서비스")
@ExtendWith({MockitoExtension.class})
class ExchangeServiceTest {
    @InjectMocks
    ExchangeService exchangeService;

    @Test
    @DisplayName("수취 금액 계산하기")
    public void testReceviedAountResponse() {
        // given
        GetReceivedAmountRequest dto = new GetReceivedAmountRequest("KWD", 1000L);

        // when
        GetReceivedAmountResponse result = exchangeService.calcReceivedAmount(dto);

        // then
        assertThat(result.getReceivedAmount()).isEqualTo(1L);
    }

    private class GetReceivedAmountResponse {
        private BigDecimal receivedAmount;

        public GetReceivedAmountResponse(BigDecimal receivedAmount) {
            this.receivedAmount = receivedAmount;
        }

        public Long getReceivedAmount() {
            return receivedAmount.longValue();
        }
    }

    private class GetReceivedAmountRequest {
        private String country;
        private BigDecimal money;

        public GetReceivedAmountRequest(String country, BigDecimal money) {
            this.country = country;
            this.money = money;
        }

        public GetReceivedAmountRequest(String country, long money) {
            this(country, BigDecimal.valueOf(money));
        }
    }
}