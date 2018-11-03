# projeto-adsd 2018.2

  O projeto consiste em um sistema que implementa uma API REST que realiza operações em banco de dados sobre serviços. A API fornece o CRUD de destes serviços:
  
   *  `POST` serviço: Onde usuários cadastram serviços prestados;
   *  `GET` serviço: Retorna uma lista com os prestadores de serviços, podendo ser filtrado por função;
   *  **TODO** `PUT` serviço: Edita dados de um prestador de serviços;
   *  **TODO** `DELETE` serviço: Apaga dados de um prestador de serviços.
  
  Para a parte de simulação, teriamos os seguintes componentes:
  
  * `Gerador de requisições Web`: responsável por gerenciar requisições provenientes de computadores;
  * `Gerador de requisições Mobile`: responsável por gerenciar requisições provenientes de dispositivos móveis;
  * `Dispatcher`: responsável por receber as requisições e repassar para o componente correto;
  * `Banco de Dados`: responsável pela persistência dos dados.
