package br.com.bigchatbrasil.services;

import br.com.bigchatbrasil.enums.Plano;
import br.com.bigchatbrasil.models.Cliente;
import br.com.bigchatbrasil.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente alterarLimite(Long id, BigDecimal novoLimite) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setLimite(novoLimite);
        return clienteRepository.save(cliente);
    }

    public Cliente alterarPlano(Long id, Plano plano) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setPlano(plano);
        return clienteRepository.save(cliente);
    }

    public Cliente incluirCreditos(Long id, BigDecimal creditos) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setSaldo(cliente.getSaldo().add(creditos));
        return clienteRepository.save(cliente);
    }

    public BigDecimal consultarSaldo(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getSaldo();
    }
}
