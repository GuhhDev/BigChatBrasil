package br.com.bigchatbrasil.controllers;

import br.com.bigchatbrasil.dtos.AdicionarCreditosRequestDTO;
import br.com.bigchatbrasil.dtos.AlterarLimiteRequestDTO;
import br.com.bigchatbrasil.dtos.AlterarPlanoRequestDTO;
import br.com.bigchatbrasil.dtos.ClienteResponseDTO;
import br.com.bigchatbrasil.models.Cliente;
import br.com.bigchatbrasil.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.cadastrarCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarClientePorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodosClientes() {
        return ResponseEntity.ok(clienteService.listarTodosClientes());
    }

    @PutMapping("/{id}/limite")
    public ResponseEntity<Cliente> alterarLimite(@Valid @RequestBody AlterarLimiteRequestDTO dto) {
        return ResponseEntity.ok(clienteService.alterarLimite(dto));
    }

    @PutMapping("/{id}/plano")
    public ResponseEntity<ClienteResponseDTO> alterarPlano(@Valid @RequestBody AlterarPlanoRequestDTO requestDTO) {

        Cliente clienteAtualizado = clienteService.alterarPlano(requestDTO);
        ClienteResponseDTO responseDTO = new ClienteResponseDTO(clienteAtualizado);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}/creditos")
    public ResponseEntity<ClienteResponseDTO> adicionarCreditos(@Valid @RequestBody AdicionarCreditosRequestDTO dto) {

        Cliente clienteAtualizado = clienteService.adicionarCreditos(dto);
        ClienteResponseDTO responseDTO = new ClienteResponseDTO(clienteAtualizado);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.consultarSaldo(id));
    }
}
