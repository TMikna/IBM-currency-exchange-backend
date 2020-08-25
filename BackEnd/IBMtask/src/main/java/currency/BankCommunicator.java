package currency;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import currency.jpa.FxRate;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

@Component
public class BankCommunicator {
	
	private List<FxRate> rates = new ArrayList<FxRate>(); 
	private int responseStatus;
	private String responseBody;
	
	//retrieve rates from bank
	private void retrieveRates() throws IOException, InterruptedException {

		// create a client
		var client = HttpClient.newHttpClient();

		// create a request
		var request = HttpRequest.newBuilder(
		       URI.create("https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=eu"))
		   .header("accept", "application/xml")
		   .build();
		// use the client to send the request
		var response = client.send(request, BodyHandlers.ofString());

		responseStatus = response.statusCode();
		responseBody = response.body();
	}
	
	private void parseRates() throws ParserConfigurationException, SAXException, IOException {
	
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(responseBody));
        Document document = dBuilder.parse(inputSource);
        
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
			  Element element = (Element) node;
			  if (element.getNodeName().contains("FxRate")) {
				  String date = element.getElementsByTagName("Dt").item(0).getTextContent();
			      
			      NodeList ccyAmounts = element.getElementsByTagName("CcyAmt");
			      Node ccyAmountNode = ccyAmounts.item(1);        // item(0) is EUR amount, which is aways 1
				  Element ccyAmountElem = (Element) ccyAmountNode;
				  
				  String currency = ccyAmountElem.getElementsByTagName("Ccy").item(0).getTextContent();
				  String amount = ccyAmountElem.getElementsByTagName("Amt").item(0).getTextContent();
				  
				  FxRate rate = new FxRate(date, currency, Double.parseDouble(amount));
				  rates.add(rate);
//				  System.out.println(rate.getCurrencyCode());
			  	}
			}
		}
		
	}
	
	public List<FxRate> getRates() throws IOException, InterruptedException, ParserConfigurationException, SAXException {
		retrieveRates();
		parseRates();
		return this.rates;
	}
	
}
