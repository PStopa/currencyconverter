package com.example.currencyconverter.domain;

import lombok.Data;

import java.util.List;

@Data
public class Euro {
    private String table;
    private String currency;
    private String code;

    private List<Rates> rates;

    @Override
    public String toString() {
        return "Euro{" +
                "table='" + table + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + rates +
                '}';
    }
}