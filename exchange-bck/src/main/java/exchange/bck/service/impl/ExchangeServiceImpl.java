package exchange.bck.service.impl;

import exchange.bck.entity.ExchangeRate;
import exchange.bck.repository.ExchangeRepository;
import exchange.bck.request.ExchangeRequest;
import exchange.bck.response.ExchangeResponse;
import exchange.bck.service.ExchangeService;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private ExchangeRepository exchangeRepository;

    @Autowired
    ExchangeServiceImpl(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public Single<ExchangeResponse> exchange(Double amount, String currencyOriginCode, String currencyDestinyCode) {
        return proccessExchange(amount, currencyOriginCode, currencyDestinyCode);
    }

    @Override
    public Single<String> update(ExchangeRequest request) {
        return proccessUpdate(request);
    }

    private Single<ExchangeResponse> proccessExchange(Double amount, String currencyOrigin, String currencyDestiny) {
        return Single.create(singleSubscriber -> {
            ExchangeRate exchangeRate = exchangeRepository.findByCurrencyOriginAndCurrencyDestiny(
                    currencyOrigin,
                    currencyDestiny
            );

            Double result = Math.round((exchangeRate.getAmount() * amount) * 100.0) / 100.0;

            ExchangeResponse exchangeResponse = new ExchangeResponse();
            exchangeResponse.setAmount(amount);
            exchangeResponse.setAmountWithExchange(result);
            exchangeResponse.setCurrencyOrigin(currencyOrigin);
            exchangeResponse.setCurrencyDestiny(currencyDestiny);
            exchangeResponse.setAmountExchange(exchangeRate.getAmount());

            singleSubscriber.onSuccess(exchangeResponse);
        });
    }

    private Single<String> proccessUpdate(ExchangeRequest request) {
        return Single.create(singleSubscriber -> {
            ExchangeRate exchangeRate = exchangeRepository.findByCurrencyOriginAndCurrencyDestiny(
                    request.getCurrencyOrigin().toUpperCase(),
                    request.getCurrencyDestiny().toUpperCase()
            );

            exchangeRate.setAmount(request.getAmount());

            String idExchangeRate = exchangeRepository.save(exchangeRate).getId();

            singleSubscriber.onSuccess(idExchangeRate);
        });
    }

}
