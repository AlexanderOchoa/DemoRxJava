package exchange.bck.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeResponse {

    private Double amount;
    private Double amountWithExchange;
    private String currencyOrigin;
    private String currencyDestiny;
    private Double amountExchange;
}
