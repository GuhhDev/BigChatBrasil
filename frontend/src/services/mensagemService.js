import axios from 'axios';

const MENSAGENS_API_BASE_URL = 'http://localhost:8080/api/mensagens';

export const enviarMensagem = async (dto) => {
  try {
    const response = await axios.post(MENSAGENS_API_BASE_URL, dto);
    return response.data;
  } catch (error) {
    console.error('Erro ao enviar mensagem', error);
    throw error;
  }
};

export const listarMensagens = async () => {
  try {
    const response = await axios.get(MENSAGENS_API_BASE_URL);
    return response.data;
  } catch (error) {
    console.error('Erro ao listar mensagens', error);
    throw error;
  }
};
