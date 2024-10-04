package br.com.bigchatbrasil.services;

import br.com.bigchatbrasil.dtos.EnviarMensagemRequestDTO;
import br.com.bigchatbrasil.dtos.MensagemResponseDTO;
import br.com.bigchatbrasil.models.Cliente;
import br.com.bigchatbrasil.models.Mensagem;
import br.com.bigchatbrasil.repositories.ClienteRepository;
import br.com.bigchatbrasil.repositories.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static br.com.bigchatbrasil.enums.Plano.POS_PAGO;
import static br.com.bigchatbrasil.enums.Plano.PRE_PAGO;

@Service
public class MensagemService {

    private static final BigDecimal CUSTO_SMS = new BigDecimal("0.25");

    private final ClienteRepository clienteRepository;
    private final MensagemRepository mensagemRepository;

    @Autowired
    public MensagemService(ClienteRepository clienteRepository, MensagemRepository mensagemRepository) {
        this.clienteRepository = clienteRepository;
        this.mensagemRepository = mensagemRepository;
    }

    public MensagemResponseDTO enviarMensagem(EnviarMensagemRequestDTO request) {
        Cliente cliente = buscarClientePorNumero(request.getNumeroTelefone());

        if (cliente == null) {
            return criarRespostaErro("Cliente não encontrado");
        }

        if (!podeEnviarMensagem(cliente)) {
            return criarRespostaErro("Saldo ou limite insuficiente para envio de mensagem");
        }

        registrarConsumo(cliente);
        return criarRespostaSucesso();
    }

    private Cliente buscarClientePorNumero(String numeroTelefone) {
        return clienteRepository.findByTelefone(numeroTelefone);
    }

    private boolean podeEnviarMensagem(Cliente cliente) {
        if (cliente.getPlano() == PRE_PAGO) {
            return clienteTemSaldoSuficiente(cliente);
        } else if (cliente.getPlano() == POS_PAGO) {
            return clienteTemLimiteSuficiente(cliente);
        }
        return false;
    }

    private boolean clienteTemSaldoSuficiente(Cliente cliente) {
        return cliente.getSaldo().compareTo(CUSTO_SMS) >= 0;
    }

    private boolean clienteTemLimiteSuficiente(Cliente cliente) {
        return cliente.getLimite().compareTo(CUSTO_SMS) >= 0;
    }

    private void registrarConsumo(Cliente cliente) {
        if (cliente.getPlano() == PRE_PAGO) {
            deduzirSaldo(cliente);
        } else if (cliente.getPlano() == POS_PAGO) {
            deduzirLimite(cliente);
        }
        clienteRepository.save(cliente);
    }

    private void deduzirSaldo(Cliente cliente) {
        cliente.setSaldo(cliente.getSaldo().subtract(CUSTO_SMS));
    }

    private void deduzirLimite(Cliente cliente) {
        cliente.setLimite(cliente.getLimite().subtract(CUSTO_SMS));
    }

    private MensagemResponseDTO criarRespostaSucesso() {
        return criarResposta("ENVIADO", "Mensagem enviada com sucesso");
    }

    private MensagemResponseDTO criarRespostaErro(String descricao) {
        return criarResposta("ERRO", descricao);
    }

    private MensagemResponseDTO criarResposta(String status, String descricao) {
        MensagemResponseDTO response = new MensagemResponseDTO();
        response.setStatus(status);
        response.setDescricao(descricao);
        return response;
    }

    public List<Mensagem> listarTodasMensagens() {
        return mensagemRepository.findAll();
    }
}
