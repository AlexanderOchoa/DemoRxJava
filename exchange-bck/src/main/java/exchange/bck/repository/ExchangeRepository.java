package exchange.bck.repository;

import exchange.bck.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeRate, String> {
    ExchangeRate findByCurrencyOriginAndCurrencyDestiny(String currencyOrigin, String currencyDestiny);
}