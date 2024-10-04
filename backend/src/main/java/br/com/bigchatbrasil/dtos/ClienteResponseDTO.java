package br.com.bigchatbrasil.dtos;

import br.com.bigchatbrasil.enums.Plano;
import br.com.bigchatbrasil.models.Cliente;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private Plano plano;
    private BigDecimal saldo;

    public ClienteResponseDTO(final Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.plano = cliente.getPlano();
        this.saldo = cliente.getSaldo();
    }
}
