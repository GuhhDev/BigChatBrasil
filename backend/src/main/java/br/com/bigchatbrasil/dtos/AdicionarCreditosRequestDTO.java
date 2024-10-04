package br.com.bigchatbrasil.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdicionarCreditosRequestDTO {
    private Long id;
    private BigDecimal creditos;
}
