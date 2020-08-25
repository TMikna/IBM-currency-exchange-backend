package currency.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends JpaRepository<FxRate, Long>
{
  List<FxRate> findByCurrencyCode(String currencyCode);
  List<FxRate> findAll();
}