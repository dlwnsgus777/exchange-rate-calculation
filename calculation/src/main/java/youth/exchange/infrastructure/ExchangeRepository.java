package youth.exchange.infrastructure;

import org.springframework.stereotype.Repository;
import youth.exchange.domain.Exchange;

import java.util.Optional;

@Repository
public interface ExchangeRepository {
    Optional<Exchange> getByCode(String code);
}
