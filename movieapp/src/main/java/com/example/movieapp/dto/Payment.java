package com.example.movieapp.dto;

import com.example.movieapp.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

//db'ye ekle
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private LocalDateTime paymentDate;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    private BigDecimal amount;

    @Override
    public String toString() {
        return "Payment{" +
                "localDateTime=" + paymentDate +
                ", currencyType=" + currencyType +
                ", amount=" + amount +
                '}';
    }
}