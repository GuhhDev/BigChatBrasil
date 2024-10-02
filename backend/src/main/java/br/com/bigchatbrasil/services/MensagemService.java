package br.com.bigchatbrasil.services;

import br.com.bigchatbrasil.enums.Plano;
import br.com.bigchatbrasil.enums.StatusEnvio;
import br.com.bigchatbrasil.models.Cliente;
import br.com.bigchatbrasil.models.Mensagem;
import br.com.bigchatbrasil.repositories.MensagemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final ClienteService clienteService;
    private static final BigDecimal CUSTO_SMS = new BigDecimal("0.25");

    public MensagemService(MensagemRepository mensagemRepository, ClienteService clienteService) {
        this.mensagemRepository = mensagemRepository;
        this.clienteService = clienteService;
    }

    public Mensagem enviarMensagem(Long clienteId, Mensagem mensagem) {
        Cliente cliente = clienteService.buscarClientePorId(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (cliente.getPlano() == Plano.PRE_PAGO) {
            if (cliente.getSaldo().compareTo(CUSTO_SMS) < 0) {
                throw new RuntimeException("Saldo insuficiente");
            }
            cliente.setSaldo(cliente.getSaldo().subtract(CUSTO_SMS));
        } else if (cliente.getPlano() == Plano.POS_PAGO) {
            if (cliente.getLimiteUtilizado().add(CUSTO_SMS).compareTo(cliente.getLimite()) > 0) {
                throw new RuntimeException("Limite de consumo excedido");
            }
            cliente.setLimiteUtilizado(cliente.getLimiteUtilizado().add(CUSTO_SMS));
        }

        mensagem.setCliente(cliente);
        mensagem.setDataEnvio(LocalDateTime.now());
        mensagem.setStatusEnvio(StatusEnvio.ENVIADO);
        return mensagemRepository.save(mensagem);
    }
}
