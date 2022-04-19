package ru.iteco.accountbank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.iteco.accountbank.model.AllCurrency;

@Component
@Slf4j
class ExchangeApiImpl implements ExchangeApi {

    @Value("${service.exchange.url.all-exchange}")
    private String urlAllCurrency;

    @Value("${service.exchange.token}")
    private String token;

    private final RestTemplate restTemplate;

    ExchangeApiImpl(@Qualifier("exchangeRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AllCurrency getAllCurrency() {
        ResponseEntity<AllCurrency> responseEntity = restTemplate.getForEntity(
                String.format(urlAllCurrency, token),
                AllCurrency.class
        );
        log.info("Received headers: {}", responseEntity.getHeaders());
        if (responseEntity.getStatusCode().isError()) {
            log.error("Received error! Message: {}", responseEntity);
        }
        return responseEntity.getBody();
    }
}
