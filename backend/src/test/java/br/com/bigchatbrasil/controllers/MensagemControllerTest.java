package br.com.bigchatbrasil.controllers;

import br.com.bigchatbrasil.JacksonTest;
import br.com.bigchatbrasil.dtos.EnviarMensagemRequestDTO;
import br.com.bigchatbrasil.dtos.MensagemResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JacksonTest
public class MensagemControllerTest {

    @Autowired
    private JacksonTester<EnviarMensagemRequestDTO> jsonRequest;

    @Autowired
    private JacksonTester<MensagemResponseDTO> jsonResponse;

    private EnviarMensagemRequestDTO requestDTO;

    private MensagemResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new EnviarMensagemRequestDTO();
        requestDTO.setNumeroTelefone("5551999999999");
        requestDTO.setWhatsApp(true);
        requestDTO.setTexto("Olá, esta é uma mensagem de teste!");

        responseDTO = new MensagemResponseDTO();
        responseDTO.setStatus("ENVIADO");
        responseDTO.setDescricao("Mensagem enviada com sucesso");
    }

    @Test
    public void testRequestMessage() throws Exception {
        final var expectedTelefone = "5551999999999";
        final var expectedIsWhatsApp = true;
        final var expectedTexto = "Olá, esta é uma mensagem de teste!";

        final var actualJson = this.jsonRequest.write(requestDTO);

        assertThat(actualJson)
                .hasJsonPathValue("$.numero_telefone", expectedTelefone)
                .hasJsonPathValue("$.whats_app", expectedIsWhatsApp)
                .hasJsonPathValue("$.texto", expectedTexto);
    }

    @Test
    public void testResponseMessage() throws Exception {
        final var expectedStatus = "ENVIADO";
        final var expectedDescricao = "Mensagem enviada com sucesso";

        final var actualJson = this.jsonResponse.write(responseDTO);

        assertThat(actualJson)
                .hasJsonPathValue("$.status", expectedStatus)
                .hasJsonPathValue("$.descricao", expectedDescricao);
    }
}
