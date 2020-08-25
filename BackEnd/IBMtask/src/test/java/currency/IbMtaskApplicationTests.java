package currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.persistence.GeneratedValue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import currency.jpa.FxRate;
import currency.jpa.Repository;

@SpringBootTest
class IbMtaskApplicationTests {

	@Autowired
	private IbMtaskApplication controller;
	
	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Autowired
	private Repository repository;
	
	@Test
	void userGetsBackFromRepositoryCorrecly() {
//		FxRate rate = new FxRate(Long.valueOf(101), "2020-08-24", "UNQ", 1.78); id is @GeneratedValue by DB, if we set it it gets changed, test fails
		FxRate rate = new FxRate("2020-08-24", "UNQ", 1.78);
		repository.save(rate);
		List<FxRate> retrievedRates = repository.findByCurrencyCode("UNQ");
		FxRate retrievedRate = retrievedRates.get(0);
		assertThat(rate.getDate()).isEqualTo(retrievedRate.getDate());
		assertThat(rate.getCurrencyCode()).isEqualTo(retrievedRate.getCurrencyCode());
//		assertEquals(rate.getId(), retrievedRate.getId());
		assertEquals(rate.getRate(), retrievedRate.getRate());
	}

}
