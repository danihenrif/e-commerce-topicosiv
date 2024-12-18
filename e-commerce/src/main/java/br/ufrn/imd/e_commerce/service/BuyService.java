package br.ufrn.imd.e_commerce.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ufrn.imd.e_commerce.model.BuyDTO;
import br.ufrn.imd.e_commerce.model.Product;

import java.util.Optional;
import java.util.UUID;

@Service
public class BuyService {

	private final RestTemplate restTemplate;

	final String URI_PRODUCT = "http://storeservice:8080/product/";
	final String URI_SELL = "http://storeservice:8080/sell/";
	final String URI_EXCHANGE = "http://exchangeservice:8080/exchange";
	final String URI_BONUS = "http://fidelity:8080/bonus/";

	public BuyService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Product buy(BuyDTO buyObject){
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

		return null;

		// ResponseEntity<UUID> responseSell = restTemplate.postForEntity(URI_SELL +
		// buyObject.getId(), null, UUID.class);
		// ResponseEntity responseBonus = restTemplate.postForEntity(URI_BONUS, new
		// BonusDTO(
		// buyObject.getIdUser(),
		// (int) product.getValue()
		// ), null);
	}

	public Double callExchange(BuyDTO buyObject) {
		ResponseEntity<Double> responseExchange = restTemplate.getForEntity(URI_EXCHANGE, Double.class);
		return responseExchange.getBody();
	}

}
