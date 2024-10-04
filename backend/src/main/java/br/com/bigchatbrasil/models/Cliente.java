package br.com.bigchatbrasil.models;

import br.com.bigchatbrasil.enums.Plano;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Plano plano;

    private String nome;
    private String email;
    private String telefone;
    private String cpfResponsavel;
    private String cnpj;
    private String nomeEmpresa;
    private BigDecimal saldo;
    private BigDecimal limite;
    private BigDecimal limiteUtilizado;
}