package br.ufrn.imd.e_commerce.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ufrn.imd.e_commerce.model.BonusDTO;
import br.ufrn.imd.e_commerce.model.BuyDTO;
import br.ufrn.imd.e_commerce.model.Product;
import br.ufrn.imd.e_commerce.model.SellDTO;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.timelimiter.TimeLimiter;
import br.ufrn.imd.e_commerce.exception.CrashException;
import br.ufrn.imd.e_commerce.exception.ErrorException;
import br.ufrn.imd.e_commerce.exception.OmissionException;
import br.ufrn.imd.e_commerce.exception.TimeException;

@Service
public class BuyService {

	private final RestTemplate restTemplate;
	private static Double taxaDeCambio = 0.0;
	private final CircuitBreaker circuitBreaker;
	private final TimeLimiter timeLimiter;

	final String URI_PRODUCT = "http://storeservice:8080/product/";
	final String URI_SELL = "http://storeservice:8080/sell";
	final String URI_EXCHANGE = "http://exchangeservice:8080/exchange/";
	final String URI_BONUS = "http://fidelity:8080/bonus/";

	public BuyService(RestTemplate restTemplate, CircuitBreaker circuitBreaker, TimeLimiter timeLimiter) {
		this.restTemplate = restTemplate;
		this.circuitBreaker = circuitBreaker;
		this.timeLimiter = timeLimiter;
	}
	
	public ResponseEntity buy(BuyDTO buyObject) {
		try {
			Product product = getProduct(buyObject);
			Double value = getExchange(buyObject);
			UUID idCompra = getSell(buyObject);
			
			try {
				getFidelity(buyObject, product.getValue());
			} catch (TimeException e) {
				
			}
			
			return ResponseEntity.ok(idCompra);
			
		} catch (OmissionException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (CrashException e) {
			return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
		} catch (ErrorException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
			
			if (taxaDeCambio == 0.0) {
				throw new CrashException("Servidor de taxa de cambio fora do ar");
			}
			
			return taxaDeCambio;
		}
	}
	
	public UUID getSell(BuyDTO buyObject) {
		
		int quant = 1;
		if (buyObject.isFt()) {
			quant = 5;
		}
		
		for (int i = 0; i < quant; i++) {
			try {
				var headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				var entity = new HttpEntity<>(new SellDTO(buyObject.getId()), headers);
			
				ResponseEntity<UUID> responseSell = restTemplate.postForEntity(
					URI_SELL,
					entity,
					UUID.class
				);
				
				return responseSell.getBody();
			} catch (RuntimeException e) {
				
			}
		}
		
		throw new ErrorException("Erro interno do servidor ao tentar realziara venda");
	}
	
	public void getFidelity(BuyDTO buyObject, double valueProduct) {
		try {
			var headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			var entity = new HttpEntity<>(
				new BonusDTO(buyObject.getIdUser(), (int) valueProduct),
		 		headers
			);
			
			ResponseEntity responseBonus = restTemplate.postForEntity(URI_BONUS, entity, null);
		} catch (Exception e) {
			
		}
	}

}
