package exchange.bck.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "exchange_rates")
@Getter
@Setter
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "currency_origin")
    private String currencyOrigin;

    @Column(name = "currency_destiny")
    private String currencyDestiny;

    private Double amount;
}
