package exchange.bck.repository;

import exchange.bck.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeRate, String> {
    ExchangeRate findByCurrencyOriginAndCurrencyDestinyAndDate(String currencyOrigin, String currencyDestiny, Date date);
}