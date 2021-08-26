package com.example.currencyconverter.controller;

import com.example.currencyconverter.domain.Euro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class CurrencyController {
    private static double euroValue = 0.0;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ModelAndView show(){
        return new ModelAndView("index", "value", euroValue);
    }
    @PostMapping("/currency")
    public ModelAndView convertedValue(@RequestParam Double value){
        final String url = "http://api.nbp.pl/api/exchangerates/rates/a/eur/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        try {
            Euro convertedToJson = objectMapper.readValue(result, Euro.class);
            double euros = convertedToJson.getRates().get(0).getMid();
            euroValue = value / euros;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/");
    }
}