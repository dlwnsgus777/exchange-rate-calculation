package youth.exchange.application.dto;

import java.math.BigDecimal;

public class GetReceivedAmountResponse {
    private Double receivedAmount;

    public GetReceivedAmountResponse(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }
}
