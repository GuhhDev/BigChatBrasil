import axios from 'axios';

const CLIENTES_API_BASE_URL = 'http://localhost:8080/api/clientes';

export const cadastrarCliente = async (clienteData) => {
  try {
    const response = await axios.post(CLIENTES_API_BASE_URL, clienteData);
    return response.data;
  } catch (error) {
    console.error('Erro ao cadastrar cliente', error);
    throw error;
  }
};

export const listarClientes = async () => {
  try {
    const response = await axios.get(CLIENTES_API_BASE_URL);
    return response.data;
  } catch (error) {
    console.error('Erro ao listar clientes', error);
    throw error;
  }
};

export const consultarSaldo = async (clienteId) => {
  try {
    const response = await axios.get(`${CLIENTES_API_BASE_URL}/${clienteId}/saldo`);
    return response.data;
  } catch (error) {
    console.error('Erro ao consultar saldo', error);
    throw error;
  }
};
