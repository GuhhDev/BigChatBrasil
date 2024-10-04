package br.com.bigchatbrasil.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AlterarLimiteRequestDTO {

    private Long id;

    private BigDecimal limite;
}
