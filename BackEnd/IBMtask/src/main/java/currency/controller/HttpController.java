package currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import currency.BankCommunicator;
import currency.jpa.FxRate;
import currency.jpa.Repository;
import currency.logging.LogRepository;
import currency.logging.userAction;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

@RestController
public class HttpController {

//    @Autowired
//    Repository repository;
//    
//    @Autowired
//    BankCommunicator bank;
    
    private final BankCommunicator bank;
    private final Repository repository;
    private final LogRepository logRepository;
    
    HttpController(BankCommunicator bank, Repository repository, LogRepository logRepository){
		this.bank = bank;
		this.repository = repository;
		this.logRepository = logRepository;
	}
    
	@GetMapping("getrate/{currencyCode}")
	public @ResponseBody ResponseEntity<String> toForeign (@PathVariable String currencyCode) {
		
		Double amount = null;
        
		for(FxRate rate: repository.findByCurrencyCode(currencyCode)){
            if(rate.getCurrencyCode().equalsIgnoreCase(currencyCode) && rate.getDate().equals(java.time.LocalDate.now().toString())) //Check data to ensure it's not outdated info
            	amount = rate.getRate();
        }
		if (amount == null) {
			List<FxRate> rates = null;
			try {
				rates = bank.getRates();
			} catch (IOException | InterruptedException | ParserConfigurationException | SAXException e) {
				// TODO Manage exceptions
				e.printStackTrace();
			}
			if (rates != null)
			for(FxRate rate: rates) {
				repository.save(rate);
	            if(rate.getCurrencyCode().equalsIgnoreCase(currencyCode))  // do not check date because we have newest data and bank may not have current day information
	            	amount = rate.getRate();
	        }
		}
		
		//TODO: if (amount == null) {return request error}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		   responseHeaders.set("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<String>(amount.toString(), responseHeaders, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("log")
	public @ResponseBody ResponseEntity<String> newUserAction(@RequestBody @ModelAttribute userAction uAction) {

		logRepository.save(uAction);

//      repository check		
//		for(userAction act: logRepository.findAll())
//			System.out.println(act);
		
//		HttpHeaders responseHeaders = new HttpHeaders();
//		   responseHeaders.set("Access-Control-Allow-Origin", "*");		
		return new ResponseEntity<String>("Log saved", HttpStatus.OK);
	}
}