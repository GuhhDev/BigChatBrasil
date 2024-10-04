INSERT INTO mensagem (cliente_id, status_envio, numero_telefone, is_whats_app, texto, data_envio) VALUES
(1, 'ENVIADO', '1234567890', true, 'Mensagem de teste 1', '2024-10-04 10:00:00'),
(2, 'FALHA', '0987654321', false, 'Mensagem de teste 2', '2024-10-03 15:30:00'),
(3, 'ENVIADO', '1122334455', true, 'Mensagem de teste 3', '2024-10-02 08:45:00'),
(4, 'PENDENTE', '6677889900', true, 'Mensagem de teste 4', '2024-10-01 17:10:00'),
(5, 'FALHA', '5566778899', false, 'Mensagem de teste 5', '2024-09-30 11:20:00');


INSERT INTO usuario (username, senha, papel) VALUES
('admin', 'senhaAdmin123', 'FINANCEIRO'),
('user1', 'senhaUser1', 'CLIENTE'),
('user2', 'senhaUser2', 'CLIENTE'),
('moderator', 'senhaMod123', 'FINANCEIRO'),
('guest', 'senhaGuest', 'FINANCEIRO');
