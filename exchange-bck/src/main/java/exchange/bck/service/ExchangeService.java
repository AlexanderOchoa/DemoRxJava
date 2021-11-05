package exchange.bck.service;

import exchange.bck.request.ExchangeRequest;
import exchange.bck.response.ExchangeResponse;
import io.reactivex.Single;

public interface ExchangeService {
    Single<ExchangeResponse> exchange(Double amount, String currencyOrigin, String currencyDestiny);
    Single<String> update(ExchangeRequest request);
}
