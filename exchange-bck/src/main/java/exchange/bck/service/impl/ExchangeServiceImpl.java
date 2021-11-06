package exchange.bck.service.impl;

import exchange.bck.entity.ExchangeRate;
import exchange.bck.repository.ExchangeRepository;
import exchange.bck.request.ExchangeRequest;
import exchange.bck.response.ExchangeResponse;
import exchange.bck.service.ExchangeService;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private ExchangeRepository exchangeRepository;

    @Autowired
    ExchangeServiceImpl(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public Single<ExchangeResponse> exchange(Double amount, String currencyOriginCode, String currencyDestinyCode, String date) {
        return proccessExchange(amount, currencyOriginCode, currencyDestinyCode, date);
    }

    @Override
    public Single<String> update(ExchangeRequest request) {
        return proccessUpdate(request);
    }

    private Single<ExchangeResponse> proccessExchange(Double amount, String currencyOrigin, String currencyDestiny, String date) {
        return Single.create(singleSubscriber -> {
            ExchangeRate exchangeRate = exchangeRepository.findByCurrencyOriginAndCurrencyDestinyAndDate(
                    currencyOrigin,
                    currencyDestiny,
                    getDateFromString(date)
            );

            Double result = Math.round((exchangeRate.getAmount() * amount) * 100.0) / 100.0;

            ExchangeResponse exchangeResponse = new ExchangeResponse();
            exchangeResponse.setAmount(amount);
            exchangeResponse.setAmountWithExchange(result);
            exchangeResponse.setCurrencyOrigin(currencyOrigin);
            exchangeResponse.setCurrencyDestiny(currencyDestiny);
            exchangeResponse.setAmountExchange(exchangeRate.getAmount());
            exchangeResponse.setDate(getStringFromDate(exchangeRate.getDate()));

            singleSubscriber.onSuccess(exchangeResponse);
        });
    }

    private Single<String> proccessUpdate(ExchangeRequest request) {
        return Single.create(singleSubscriber -> {
            ExchangeRate exchangeRate = exchangeRepository.findByCurrencyOriginAndCurrencyDestinyAndDate(
                    request.getCurrencyOrigin().toUpperCase(),
                    request.getCurrencyDestiny().toUpperCase(),
                    getDateFromString(request.getDate())
            );

            exchangeRate.setAmount(request.getAmount());

            String idExchangeRate = exchangeRepository.save(exchangeRate).getId();

            singleSubscriber.onSuccess(idExchangeRate);
        });
    }

    private Date getDateFromString(String date) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(date);
    }

    private String getStringFromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

}