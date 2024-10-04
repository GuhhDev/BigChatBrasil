package br.com.bigchatbrasil.services;

import br.com.bigchatbrasil.dtos.AdicionarCreditosRequestDTO;
import br.com.bigchatbrasil.dtos.AlterarLimiteRequestDTO;
import br.com.bigchatbrasil.dtos.AlterarPlanoRequestDTO;
import br.com.bigchatbrasil.exceptions.ClienteNaoEncontradoException;
import br.com.bigchatbrasil.models.Cliente;
import br.com.bigchatbrasil.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorId(Long id) {
        return obterClientePorId(id);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente alterarLimite(AlterarLimiteRequestDTO dto) {
        Cliente cliente = obterClientePorId(dto.getId());
        cliente.setLimite(dto.getLimite());
        return clienteRepository.save(cliente);
    }

    public Cliente alterarPlano(AlterarPlanoRequestDTO dto) {
        Cliente cliente = obterClientePorId(dto.getId());
        cliente.setPlano(dto.getPlano());
        return clienteRepository.save(cliente);
    }

    public Cliente adicionarCreditos(AdicionarCreditosRequestDTO dto) {
        Cliente cliente = obterClientePorId(dto.getId());
        atualizarSaldo(cliente, dto.getCreditos());
        return clienteRepository.save(cliente);
    }

    public BigDecimal consultarSaldo(Long id) {
        Cliente cliente = obterClientePorId(id);
        return cliente.getSaldo();
    }

    private Cliente obterClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    private void atualizarSaldo(Cliente cliente, BigDecimal creditos) {
        BigDecimal novoSaldo = cliente.getSaldo().add(creditos);
        cliente.setSaldo(novoSaldo);
    }
}
