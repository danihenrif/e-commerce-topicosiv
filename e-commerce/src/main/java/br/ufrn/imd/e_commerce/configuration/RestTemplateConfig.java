package br.ufrn.imd.e_commerce.configuration;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
//        httpRequestFactory.setConnectionRequestTimeout(1000);
//        httpRequestFactory.setConnectTimeout(1000);
//        httpRequestFactory.setReadTimeout(1000);
    	var factory = new SimpleClientHttpRequestFactory();
    	
//        factory.setConnectTimeout(1000);
//        factory.setReadTimeout(1000);
        
        return new RestTemplate(factory);
    }
    
    @Bean
    public CircuitBreaker circuitBreaker() {
    	CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50) 
                .waitDurationInOpenState(Duration .ofSeconds(1))
                .slidingWindowSize(10) 
                .build();
    	
    	CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
    	
    	return registry.circuitBreaker("circuitBreaker");
    }
    
    @Bean
    public TimeLimiter timeLimiter() {
    	TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(1)) 
                .build();
    	
    	return TimeLimiter.of(timeLimiterConfig);
    }

}
