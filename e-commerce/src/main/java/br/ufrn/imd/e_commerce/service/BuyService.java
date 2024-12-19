package br.ufrn.imd.e_commerce.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ufrn.imd.e_commerce.model.BuyDTO;
import br.ufrn.imd.e_commerce.model.Product;

import br.ufrn.imd.e_commerce.exception.OmissionException;

@Service
public class BuyService {

	private final RestTemplate restTemplate;
	private static Double taxaDeCambio = 0.0;

	final String URI_PRODUCT = "http://storeservice:8080/product/";
	final String URI_SELL = "http://storeservice:8080/sell/";
	final String URI_EXCHANGE = "http://exchangeservice:8080/exchange/";
	final String URI_BONUS = "http://fidelity:8080/bonus/";

	public BuyService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public ResponseEntity buy(BuyDTO buyObject) {
		try {
			Product product = getProduct(buyObject);
//			getExchange(buyObject);
			
			return ResponseEntity.ok(product);
			
		} catch (OmissionException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	public Product getProduct(BuyDTO buyObject) {
		int quant = 1;
		if (buyObject.isFt()) {
			quant = 5;
		}

		for (int i = 0; i < quant; i++) {
			try {
				ResponseEntity<Product> responseProduct = restTemplate.getForEntity(URI_PRODUCT + buyObject.getId(),
						Product.class);
				Product product = responseProduct.getBody();
				
				return product;
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		throw new OmissionException("Produto não encontrado");

	}

	public Double getExchange(BuyDTO buyObject) {
		try {
			ResponseEntity<Double> responseExchange = restTemplate.getForEntity(URI_EXCHANGE + buyObject.getId(),
					Double.class);
			taxaDeCambio = responseExchange.getBody();
			return responseExchange.getBody();
		} catch (RuntimeException e) {
			return taxaDeCambio + 1; // Somando 1 para mostrar que está pegando do cache
		}
	}

	//TODO: Bonus,Sell;
	//TODO:Juntar as requisições em uma
	//TODO: Replicas para exchange;
	// ResponseEntity responseBonus = restTemplate.postForEntity(URI_BONUS, new BonusDTO(
		// 		buyObject.getIdUser(),
		// 		(int) product.getValue()), null);

}
