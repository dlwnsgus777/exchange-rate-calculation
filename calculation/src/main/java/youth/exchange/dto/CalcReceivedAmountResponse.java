package youth.exchange.dto;

public class CalcReceivedAmountResponse {
    private Double receivedAmount;

    public CalcReceivedAmountResponse(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }
}
