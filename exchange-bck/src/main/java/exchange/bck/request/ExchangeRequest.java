package exchange.bck.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExchangeRequest {

    private Double amount;
    private String currencyOrigin;
    private String currencyDestiny;
    private String date;
}
