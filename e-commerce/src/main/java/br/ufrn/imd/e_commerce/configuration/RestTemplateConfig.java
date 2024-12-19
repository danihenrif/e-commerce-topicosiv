package br.ufrn.imd.e_commerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
//        httpRequestFactory.setConnectionRequestTimeout(1000);
//        httpRequestFactory.setConnectTimeout(1000);
//        httpRequestFactory.setReadTimeout(1000);
    	var factory = new SimpleClientHttpRequestFactory();

//        factory.setConnectTimeout(3000);
//        factory.setReadTimeout(3000);
        
        return new RestTemplate(factory);
    }

}
