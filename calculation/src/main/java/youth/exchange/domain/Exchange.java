package youth.exchange.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youth.common.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "exchanges")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PUBLIC)
public class Exchange extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Double exchangeRate;
    private String country;

    public Double exchangeRate() {
        return exchangeRate;
    }

    public void updateExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
