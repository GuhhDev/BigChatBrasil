package br.com.bigchatbrasil.dtos;

import lombok.Data;

@Data
public class EnviarMensagemRequestDTO {
    private Long clienteId;
    private String numeroTelefone;
    private String texto;
    private boolean isWhatsApp;

}
