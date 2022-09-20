package org.example.currencyexchange.service;

import org.example.currencyexchange.entity.ExchangeValue;
import org.example.currencyexchange.repository.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeValueService {
    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    public ExchangeValue findByFromAndTo(String from, String to) throws Exception {
        return exchangeValueRepository.findByFromAndTo(from, to).orElseThrow(() -> new Exception("Not found exchange value"));
    }
}
