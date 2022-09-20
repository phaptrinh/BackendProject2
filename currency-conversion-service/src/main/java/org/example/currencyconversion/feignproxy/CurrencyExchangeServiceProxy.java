package org.example.currencyconversion.feignproxy;

import org.example.currencyconversion.payload.CalculatedAmount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CalculatedAmount getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
