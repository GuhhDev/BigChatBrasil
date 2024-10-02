package br.com.bigchatbrasil.models;

import br.com.bigchatbrasil.enums.StatusEnvio;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private StatusEnvio statusEnvio;

    private String numeroTelefone;
    private boolean isWhatsApp;
    private String texto;
    private LocalDateTime dataEnvio;
}