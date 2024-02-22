# language: pt
@fare
Funcionalidade: Receber pedidos de Cadastro de Usuário e Validar Saida
  Como um micro serviço especializado em criação de novos usuários, escuto eventos do Kafka,
  para devolver a informação do cadastro através de outro evento enviado ao Kafka.

  @success
  Cenario: Um evento é recebido e processado com Sucesso
    Dado que uma mensagem "Bem vindo" é enviada no tópico "users"
    Quando o micro-serviço receber o evento, processar e devolver uma resposta
    Então a mensagem retornada deve conter "Bem vindo"