package youth.exchange.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter(value = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CalcReceivedAmountRequest {
    private String code;

    @Min(value = 1, message = "0보다 작을 수 없습니다.")
    @Max(value = 10_000, message = "10,000 보다 클 수 없습니다.")
    @NotNull(message = "수취 금액을 입력해주세요.")
    private Long money;
}
