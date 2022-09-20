package org.example.currencyconversion.service;

import org.example.currencyconversion.feignproxy.CurrencyExchangeServiceProxy;
import org.example.currencyconversion.payload.CalculatedAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConversionService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @Autowired
    Environment environment;

    public CalculatedAmount convert(String from, String to, BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CalculatedAmount> response =
                restTemplate.getForEntity("http://CURRENCY-EXCHANGE-SERVICE/currency-exchange/from/{from}/to/{to}",
                                            CalculatedAmount.class,
                                            uriVariables);
        CalculatedAmount calculatedAmount = response.getBody();
        BigDecimal amount = quantity.multiply(calculatedAmount.getConversionMultiple());
        calculatedAmount.setQuantity(quantity);
        calculatedAmount.setTotalCalculatedAmount(amount);
        calculatedAmount.setConversionServicePort(Integer.parseInt(environment.getProperty("local.server.port")));
        return calculatedAmount;
    }

    public CalculatedAmount convertFeign(String from, String to, BigDecimal quantity) {
        CalculatedAmount response = proxy.getExchangeValue(from, to);
        BigDecimal amount = quantity.multiply(response.getConversionMultiple());
        response.setQuantity(quantity);
        response.setTotalCalculatedAmount(amount);
        response.setConversionServicePort(Integer.parseInt(environment.getProperty("local.server.port")));
        return response;
    }

}
