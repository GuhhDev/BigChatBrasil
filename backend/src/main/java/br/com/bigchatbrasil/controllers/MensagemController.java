package br.com.bigchatbrasil.controllers;

import br.com.bigchatbrasil.models.Mensagem;
import br.com.bigchatbrasil.services.MensagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mensagens")
public class MensagemController {

    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<Mensagem> enviarMensagem(@PathVariable Long clienteId, @RequestBody Mensagem mensagem) {
        return ResponseEntity.ok(mensagemService.enviarMensagem(clienteId, mensagem));
    }
}