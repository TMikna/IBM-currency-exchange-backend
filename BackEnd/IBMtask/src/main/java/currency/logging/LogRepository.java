package currency.logging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<userAction, Long>
{
  List<userAction> findByUserId(String userId);
  List<userAction> findAll();
}