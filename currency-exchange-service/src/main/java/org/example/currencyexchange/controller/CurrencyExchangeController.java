package org.example.currencyexchange.controller;

import org.example.currencyexchange.entity.ExchangeValue;
import org.example.currencyexchange.service.ExchangeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    @Autowired
    ExchangeValueService exchangeValueService;

    @Autowired
    Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) throws Exception {
        ExchangeValue exchangeValue = exchangeValueService.findByFromAndTo(from, to);
        exchangeValue.setExchangeServicePort(Integer.parseInt(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
