package br.com.bigchatbrasil.controllers;

import br.com.bigchatbrasil.enums.Plano;
import br.com.bigchatbrasil.models.Cliente;
import br.com.bigchatbrasil.services.ClienteService;
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
        return clienteService.buscarClientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodosClientes() {
        return ResponseEntity.ok(clienteService.listarTodosClientes());
    }

    @PutMapping("/{id}/limite")
    public ResponseEntity<Cliente> alterarLimite(@PathVariable Long id, @RequestParam BigDecimal novoLimite) {
        return ResponseEntity.ok(clienteService.alterarLimite(id, novoLimite));
    }

    @PutMapping("/{id}/plano")
    public ResponseEntity<Cliente> alterarPlano(@PathVariable Long id, @RequestParam Plano plano) {
        return ResponseEntity.ok(clienteService.alterarPlano(id, plano));
    }

    @PutMapping("/{id}/creditos")
    public ResponseEntity<Cliente> incluirCreditos(@PathVariable Long id, @RequestParam BigDecimal creditos) {
        return ResponseEntity.ok(clienteService.incluirCreditos(id, creditos));
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.consultarSaldo(id));
    }
}
