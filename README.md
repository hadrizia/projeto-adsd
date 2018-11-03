# Projeto ADSD 2018.2
  
  Projeto implementado para a disciplina de Análise de Desempenho de Sistemas Discretos durante o período de 2018.2. O grupo é composto por:
  
    * Ana Godoy
    * Hadrizia Santos

## Descrição do Projeto

  O projeto consiste em um sistema que implementa uma API REST que realiza operações em banco de dados sobre serviços. A API fornece o CRUD de destes serviços:
  
   *  `POST` serviço: Onde usuários cadastram serviços prestados;
   *  `GET` serviço: Retorna uma lista com os prestadores de serviços, podendo ser filtrado por função;
   *  **TODO** `PUT` serviço: Edita dados de um prestador de serviços;
   *  **TODO** `DELETE` serviço: Apaga dados de um prestador de serviços.
  
## Simulação
  Para a parte de simulação, utilizamos a biblioteca [SimJava](http://www.icsa.inf.ed.ac.uk/research/groups/hase/simjava/guide/tutorial.html) e abstraímos os os seguintes componentes:
  
  * `Gerador de requisições Web`: responsável por gerenciar requisições provenientes de computadores;
  * `Gerador de requisições Mobile`: responsável por gerenciar requisições provenientes de dispositivos móveis;
  * `Dispatcher`: responsável por receber as requisições e repassar para o componente correto;
  * `Banco de Dados`: responsável pela persistência dos dados.
