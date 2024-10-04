package br.com.bigchatbrasil.controllers;

import br.com.bigchatbrasil.dtos.EnviarMensagemRequestDTO;
import br.com.bigchatbrasil.dtos.MensagemResponseDTO;
import br.com.bigchatbrasil.models.Mensagem;
import br.com.bigchatbrasil.services.MensagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensagens")
public class MensagemController {

    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @PostMapping
    public ResponseEntity<MensagemResponseDTO> enviarMensagem(@RequestBody EnviarMensagemRequestDTO dto) {
        return ResponseEntity.ok(mensagemService.enviarMensagem(dto));
    }

    @GetMapping
    public ResponseEntity<List<Mensagem>> listarMensagens() {
        return ResponseEntity.ok(mensagemService.listarTodasMensagens());
    }
}