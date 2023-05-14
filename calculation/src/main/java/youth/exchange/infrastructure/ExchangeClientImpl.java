package youth.exchange.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youth.exchange.dto.ExchangeDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeClientImpl implements ExchangeClient{

    private final String apiUrl;
    private final String apiKey;

    public ExchangeClientImpl(@Value("${api.exchange_api_url}") String apiUrl,
                              @Value("${api.exchange_api_key}") String apiKey
    ) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    @Override
    public List<ExchangeDto> getExchangeRates() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        setApiCallHeaders(headers);
        ResponseEntity<ExchangeResult> result = getExchange(restTemplate, headers);
        if (isApiCallSuccess(result)) {
            ExchangeResult resultBody = result.getBody();
            if (resultBody.getSuccess()) {
                return List.of(
                        new ExchangeDto("KRW", resultBody.getQuotes().getUsdKrw()),
                        new ExchangeDto("JPY", resultBody.getQuotes().getUsdJpy()),
                        new ExchangeDto("PHP", resultBody.getQuotes().getUsdPhp())
                );
            }
        }

        return new ArrayList<>();
    }

    private static boolean isApiCallSuccess(ResponseEntity<ExchangeResult> result) {
        return result.getStatusCode() == HttpStatus.OK;
    }

    private ResponseEntity<ExchangeResult> getExchange(RestTemplate restTemplate, HttpHeaders headers) {
        return restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ExchangeResult.class
        );
    }

    private void setApiCallHeaders(HttpHeaders headers) {
        headers.set("apikey", apiKey);
        headers.add("Content-Type", "application/json");
        headers.add("Accept-Charset", "UTF-8");
    }

    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PRIVATE)
    public static class ExchangeResult {
        private Boolean success;
        private Quotes quotes;

        @Getter(value = AccessLevel.PUBLIC)
        @Setter(value = AccessLevel.PRIVATE)
        public static class Quotes {
            @JsonProperty("USDKRW")
            private Double UsdKrw;
            @JsonProperty("USDJPY")
            private Double UsdJpy;
            @JsonProperty("USDPHP")
            private Double UsdPhp;
        }
    }
}
