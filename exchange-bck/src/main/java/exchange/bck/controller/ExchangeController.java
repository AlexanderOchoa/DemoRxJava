package exchange.bck.controller;

import exchange.bck.request.ExchangeRequest;
import exchange.bck.response.GenericResponse;
import exchange.bck.service.ExchangeService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/exchanges")
public class ExchangeController {

    private Logger LOGGER = LoggerFactory.getLogger(ExchangeController.class);

    private ExchangeService exchangeService;

    @Autowired
    ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping(value = "/{amount}/{currencyOrigin}/{currencyDestiny}")
    public Single<ResponseEntity<GenericResponse>> exchange(@PathVariable Double amount,
                                                            @PathVariable String currencyOrigin,
                                                            @PathVariable String currencyDestiny) {
        LOGGER.info("Start to exchange: amount: {}, currencyOrigin: {}, currencyDestiny: {}", amount, currencyOrigin, currencyDestiny);
        return exchangeService.exchange(amount, currencyOrigin.toUpperCase(), currencyDestiny.toUpperCase())
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity.ok(GenericResponse.successWithData(s)));
    }

    @PutMapping
    public Single<ResponseEntity<GenericResponse>> update(@RequestBody ExchangeRequest request) {
        LOGGER.info("Start to update exchange: {}", request);
        return exchangeService.update(request)
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity.ok(GenericResponse.successNoData()));
    }

}
