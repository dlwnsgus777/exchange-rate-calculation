package youth.exchange.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import youth.common.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "exchanges")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exchange extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Double exchangeRate;


    public Double exchangeRate() {
        return exchangeRate;
    }

}
