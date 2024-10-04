import React, { useState } from 'react';
import { enviarMensagem, listarMensagens } from './services/mensagemService';
import { cadastrarCliente, listarClientes, consultarSaldo } from './services/clienteService';
import './App.css';

function App() {
  const [mensagemResponse, setMensagemResponse] = useState(null);
  const [mensagens, setMensagens] = useState([]);
  const [cliente, setCliente] = useState(null);
  const [clientes, setClientes] = useState([]);
  const [saldo, setSaldo] = useState(null);

  const handleEnviarMensagem = async () => {
    const dto = {
      clienteId: 1, 
      numeroTelefone: '11999999999',
      texto: 'Essa é uma mensagem de teste',
      isWhatsApp: true
    };

    try {
      const response = await enviarMensagem(dto);
      setMensagemResponse(response);
    } catch (error) {
      console.error('Erro ao enviar a mensagem');
    }
  };

  const handleListarMensagens = async () => {
    try {
      const response = await listarMensagens();
      setMensagens(response);
    } catch (error) {
      console.error('Erro ao listar mensagens');
    }
  };

  const handleCadastrarCliente = async () => {
    const clienteData = {
      nome: 'João Silva',
      telefone: '11999999999',
      email: 'joao@example.com',
      cpfResponsavel: '123.456.789-00',
      cnpj: '00.000.000/0001-00',
      nomeEmpresa: 'Empresa Teste',
      saldo: 1000.0,
      limite: 5000.0,
      limiteUtilizado: 0.0,
      plano: 'PRE_PAGO'
    };

    try {
      const response = await cadastrarCliente(clienteData);
      setCliente(response);
    } catch (error) {
      console.error('Erro ao cadastrar cliente');
    }
  };

  const handleListarClientes = async () => {
    try {
      const response = await listarClientes();
      setClientes(response);
    } catch (error) {
      console.error('Erro ao listar clientes');
    }
  };

  const handleConsultarSaldo = async () => {
    try {
      const response = await consultarSaldo(1);
      setSaldo(response);
    } catch (error) {
      console.error('Erro ao consultar saldo');
    }
  };

  return (
    <div className="App">
      <h1>API de Mensagens e Clientes</h1>

      {/* Enviar Mensagem */}
      <div>
        <button onClick={handleEnviarMensagem}>Enviar Mensagem</button>
        {mensagemResponse && (
          <div>
            <h3>Resposta da Mensagem:</h3>
            <pre>{JSON.stringify(mensagemResponse, null, 2)}</pre>
          </div>
        )}
      </div>

      {/* Listar Mensagens */}
      <div>
        <button onClick={handleListarMensagens}>Listar Mensagens</button>
        {mensagens.length > 0 && (
          <div>
            <h3>Mensagens:</h3>
            <ul>
              {mensagens.map((msg) => (
                <li key={msg.id}>{msg.texto}</li>
              ))}
            </ul>
          </div>
        )}
      </div>

      {/* Cadastrar Cliente */}
      <div>
        <button onClick={handleCadastrarCliente}>Cadastrar Cliente</button>
        {cliente && (
          <div>
            <h3>Cliente Cadastrado:</h3>
            <pre>{JSON.stringify(cliente, null, 2)}</pre>
          </div>
        )}
      </div>

      {/* Listar Clientes */}
      <div>
        <button onClick={handleListarClientes}>Listar Todos os Clientes</button>
        {clientes.length > 0 && (
          <div>
            <h3>Clientes:</h3>
            <ul>
              {clientes.map((cliente) => (
                <li key={cliente.id}>{cliente.nome}</li>
              ))}
            </ul>
          </div>
        )}
      </div>

      {/* Consultar Saldo */}
      <div>
        <button onClick={handleConsultarSaldo}>Consultar Saldo</button>
        {saldo !== null && (
          <div>
            <h3>Saldo do Cliente:</h3>
            <pre>{saldo}</pre>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
