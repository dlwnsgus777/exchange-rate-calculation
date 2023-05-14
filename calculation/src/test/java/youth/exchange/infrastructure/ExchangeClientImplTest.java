package youth.exchange.infrastructure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.springframework.http.HttpStatus;
import youth.exchange.dto.ExchangeDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockitoExtension.class)
class ExchangeClientImplTest {
    private MockServerClient mockServer;

    @InjectMocks
    ExchangeClientImpl exchangeClient;

    @BeforeEach
    public void setUpMockServer() {
        this.mockServer = ClientAndServer.startClientAndServer(1080);
        new MockServerClient("localhost", 1080).when(
                        request()
                                .withMethod("GET")
                                .withHeader("apikey", "test")
                )
                .respond(
                        response()
                                .withStatusCode(HttpStatus.OK.value())
                                .withHeader("Content-Type", "application/json")
                                .withBody(getExchangeApiResult())
                );
    }

    @AfterEach
    public void stopMockServer() {
        this.mockServer.stop();
    }
    @Test
    @DisplayName("환율 api 호출 테스트")
    void testGetExchangeToApiCall() {
        //given
        exchangeClient = new ExchangeClientImpl("http://localhost:1080", "test");

        //when
        List<ExchangeDto> result = exchangeClient.getExchangeRates();

        //then
        for (ExchangeDto exchangeDto : result) {
            if (exchangeDto.getCode().equals("KRW")) {
                assertThat(exchangeDto.getExchangeRate()).isEqualTo(1_342.510384);
            }
        }
    }


    private String getExchangeApiResult() {
        return "{\n" +
                "    \"success\": true,\n" +
                "    \"timestamp\": 1684071303,\n" +
                "    \"source\": \"USD\",\n" +
                "    \"quotes\": {\n" +
                "        \"USDAED\": 3.672104,\n" +
                "        \"USDAFN\": 87.700535,\n" +
                "        \"USDALL\": 101.421454,\n" +
                "        \"USDAMD\": 386.763563,\n" +
                "        \"USDANG\": 1.796609,\n" +
                "        \"USDAOA\": 510.000367,\n" +
                "        \"USDARS\": 228.263358,\n" +
                "        \"USDAUD\": 1.504438,\n" +
                "        \"USDAWG\": 1.8,\n" +
                "        \"USDAZN\": 1.70397,\n" +
                "        \"USDBAM\": 1.787833,\n" +
                "        \"USDBBD\": 2.012798,\n" +
                "        \"USDBDT\": 107.052425,\n" +
                "        \"USDBGN\": 1.80208,\n" +
                "        \"USDBHD\": 0.373874,\n" +
                "        \"USDBIF\": 2809.909045,\n" +
                "        \"USDBMD\": 1,\n" +
                "        \"USDBND\": 1.329951,\n" +
                "        \"USDBOB\": 6.888797,\n" +
                "        \"USDBRL\": 4.920588,\n" +
                "        \"USDBSD\": 0.996938,\n" +
                "        \"USDBTC\": 3.7135472e-05,\n" +
                "        \"USDBTN\": 81.933361,\n" +
                "        \"USDBWP\": 13.471365,\n" +
                "        \"USDBYN\": 2.516294,\n" +
                "        \"USDBYR\": 19600,\n" +
                "        \"USDBZD\": 2.009415,\n" +
                "        \"USDCAD\": 1.36555,\n" +
                "        \"USDCDF\": 2285.000362,\n" +
                "        \"USDCHF\": 0.897869,\n" +
                "        \"USDCLF\": 0.028668,\n" +
                "        \"USDCLP\": 791.032497,\n" +
                "        \"USDCNY\": 6.958204,\n" +
                "        \"USDCOP\": 4583.207642,\n" +
                "        \"USDCRC\": 533.479592,\n" +
                "        \"USDCUC\": 1,\n" +
                "        \"USDCUP\": 26.5,\n" +
                "        \"USDCVE\": 100.799854,\n" +
                "        \"USDCZK\": 21.75104,\n" +
                "        \"USDDJF\": 177.494401,\n" +
                "        \"USDDKK\": 6.86304,\n" +
                "        \"USDDOP\": 54.417478,\n" +
                "        \"USDDZD\": 135.029937,\n" +
                "        \"USDEGP\": 30.647653,\n" +
                "        \"USDERN\": 15,\n" +
                "        \"USDETB\": 54.39234,\n" +
                "        \"USDEUR\": 0.914104,\n" +
                "        \"USDFJD\": 2.221804,\n" +
                "        \"USDFKP\": 0.803227,\n" +
                "        \"USDGBP\": 0.8026,\n" +
                "        \"USDGEL\": 2.57504,\n" +
                "        \"USDGGP\": 0.803227,\n" +
                "        \"USDGHS\": 11.613876,\n" +
                "        \"USDGIP\": 0.803227,\n" +
                "        \"USDGMD\": 59.85039,\n" +
                "        \"USDGNF\": 8568.947392,\n" +
                "        \"USDGTQ\": 7.775492,\n" +
                "        \"USDGYD\": 210.841446,\n" +
                "        \"USDHKD\": 7.84775,\n" +
                "        \"USDHNL\": 24.498377,\n" +
                "        \"USDHRK\": 6.94434,\n" +
                "        \"USDHTG\": 145.052333,\n" +
                "        \"USDHUF\": 341.49504,\n" +
                "        \"USDIDR\": 14844.45,\n" +
                "        \"USDILS\": 3.67375,\n" +
                "        \"USDIMP\": 0.803227,\n" +
                "        \"USDINR\": 82.200504,\n" +
                "        \"USDIQD\": 1305.909776,\n" +
                "        \"USDIRR\": 42250.000352,\n" +
                "        \"USDISK\": 138.730386,\n" +
                "        \"USDJEP\": 0.803227,\n" +
                "        \"USDJMD\": 154.248366,\n" +
                "        \"USDJOD\": 0.709404,\n" +
                "        \"USDJPY\": 135.73504,\n" +
                "        \"USDKES\": 134.576535,\n" +
                "        \"USDKGS\": 87.303801,\n" +
                "        \"USDKHR\": 4104.39234,\n" +
                "        \"USDKMF\": 453.450384,\n" +
                "        \"USDKPW\": 900.000009,\n" +
                "        \"USDKRW\": 1342.510384,\n" +
                "        \"USDKWD\": 0.30695,\n" +
                "        \"USDKYD\": 0.830842,\n" +
                "        \"USDKZT\": 446.738882,\n" +
                "        \"USDLAK\": 17469.719823,\n" +
                "        \"USDLBP\": 14963.389552,\n" +
                "        \"USDLKR\": 312.537136,\n" +
                "        \"USDLRD\": 166.750382,\n" +
                "        \"USDLSL\": 19.340382,\n" +
                "        \"USDLTL\": 2.95274,\n" +
                "        \"USDLVL\": 0.60489,\n" +
                "        \"USDLYD\": 4.746104,\n" +
                "        \"USDMAD\": 9.969834,\n" +
                "        \"USDMDL\": 17.74487,\n" +
                "        \"USDMGA\": 4381.370264,\n" +
                "        \"USDMKD\": 56.327986,\n" +
                "        \"USDMMK\": 2093.514329,\n" +
                "        \"USDMNT\": 3466.767364,\n" +
                "        \"USDMOP\": 8.05247,\n" +
                "        \"USDMRO\": 356.999828,\n" +
                "        \"USDMUR\": 45.350379,\n" +
                "        \"USDMVR\": 15.360378,\n" +
                "        \"USDMWK\": 1023.264317,\n" +
                "        \"USDMXN\": 17.597704,\n" +
                "        \"USDMYR\": 4.462504,\n" +
                "        \"USDMZN\": 63.250377,\n" +
                "        \"USDNAD\": 19.340377,\n" +
                "        \"USDNGN\": 462.503727,\n" +
                "        \"USDNIO\": 36.464189,\n" +
                "        \"USDNOK\": 10.72476,\n" +
                "        \"USDNPR\": 131.093743,\n" +
                "        \"USDNZD\": 1.615118,\n" +
                "        \"USDOMR\": 0.38498,\n" +
                "        \"USDPAB\": 0.996846,\n" +
                "        \"USDPEN\": 3.648156,\n" +
                "        \"USDPGK\": 3.524841,\n" +
                "        \"USDPHP\": 55.864039,\n" +
                "        \"USDPKR\": 284.363454,\n" +
                "        \"USDPLN\": 4.165455,\n" +
                "        \"USDPYG\": 7200.511906,\n" +
                "        \"USDQAR\": 3.641038,\n" +
                "        \"USDRON\": 4.543704,\n" +
                "        \"USDRSD\": 108.090373,\n" +
                "        \"USDRUB\": 77.360373,\n" +
                "        \"USDRWF\": 1114.858997,\n" +
                "        \"USDSAR\": 3.750303,\n" +
                "        \"USDSBD\": 8.334311,\n" +
                "        \"USDSCR\": 13.823301,\n" +
                "        \"USDSDG\": 600.00034,\n" +
                "        \"USDSEK\": 10.47334,\n" +
                "        \"USDSGD\": 1.338504,\n" +
                "        \"USDSHP\": 1.21675,\n" +
                "        \"USDSLE\": 22.784876,\n" +
                "        \"USDSLL\": 19750.000338,\n" +
                "        \"USDSOS\": 569.000338,\n" +
                "        \"USDSRD\": 37.111504,\n" +
                "        \"USDSTD\": 20697.981008,\n" +
                "        \"USDSVC\": 8.722519,\n" +
                "        \"USDSYP\": 2512.031395,\n" +
                "        \"USDSZL\": 19.158097,\n" +
                "        \"USDTHB\": 33.923038,\n" +
                "        \"USDTJS\": 10.901047,\n" +
                "        \"USDTMT\": 3.51,\n" +
                "        \"USDTND\": 3.045038,\n" +
                "        \"USDTOP\": 2.348404,\n" +
                "        \"USDTRY\": 19.578304,\n" +
                "        \"USDTTD\": 6.766306,\n" +
                "        \"USDTWD\": 30.824404,\n" +
                "        \"USDTZS\": 2350.65588,\n" +
                "        \"USDUAH\": 36.816765,\n" +
                "        \"USDUGX\": 3710.40724,\n" +
                "        \"USDUYU\": 38.822615,\n" +
                "        \"USDUZS\": 11429.22437,\n" +
                "        \"USDVEF\": 2530847.185328,\n" +
                "        \"USDVES\": 25.316715,\n" +
                "        \"USDVND\": 23460,\n" +
                "        \"USDVUV\": 117.672373,\n" +
                "        \"USDWST\": 2.683112,\n" +
                "        \"USDXAF\": 599.622469,\n" +
                "        \"USDXAG\": 0.041731,\n" +
                "        \"USDXAU\": 0.000497,\n" +
                "        \"USDXCD\": 2.70255,\n" +
                "        \"USDXDR\": 0.738882,\n" +
                "        \"USDXOF\": 599.625211,\n" +
                "        \"USDXPF\": 109.825037,\n" +
                "        \"USDYER\": 250.350363,\n" +
                "        \"USDZAR\": 19.445495,\n" +
                "        \"USDZMK\": 9001.203589,\n" +
                "        \"USDZMW\": 18.267745,\n" +
                "        \"USDZWL\": 321.999592\n" +
                "    }\n" +
                "}";
    }

}