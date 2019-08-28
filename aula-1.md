# [SCJ - Tecnologia Webservices e RESTful](./readme.md) 

## Introdução e boas práticas de design de Web Services:

“A Web service is a software system identified by a URI [RFC 2396], whose public interfaces and bindings are defined and described using XML. Its definition can be discovered by other software systems. These systems may then interact with the Web service in a manner prescribed by its definition, using XML based messages conveyed by Internet protocols.”

https://www.w3.org/TR/wsa-reqs/#id2604831

### REST ou REpresentational State Transfer 

REST ou REpresentational State Transfer é um estilo arquitetural utilizado para construção de serviços/APIs baseados no protocolo HTTP através do uso dos verbos do protocolo e URIs.

### Princípios:
 
* **Addressable resources:**

A abstração principal de informações e dados no REST é um recurso, e cada recurso deve ser endereçável por meio de um URI (Uniform Resource Identifier).

Endereçabilidade é a ideia de que todos os objetos e recursos do seu sistema podem ser acessados por meio de um identificador exclusivo.

* **A uniform, constrained interface:**

Use um pequeno conjunto de métodos bem definidos para manipular seus recursos.

O princípio REST de uma interface restrita talvez seja a pílula mais difícil de ser engolida por um desenvolvedor experiente de CORBA ou SOAP. 

A ideia por trás disso é que você se atenha ao conjunto finito de operações do protocolo de aplicativo no qual você está distribuindo seus serviços. 

Isso significa que você não tem um parâmetro "action" em seu URI e usa apenas os métodos de HTTP para seus serviços da web. 

O HTTP tem um conjunto pequeno e fixo de métodos operacionais. Cada método tem um propósito e significado específico. 

* **Representation-oriented:**

Você interage com os serviços usando representações desse serviço. Um recurso referenciado por um URI pode ter formatos diferentes. Plataformas diferentes precisam de formatos diferentes. Por exemplo, os navegadores precisam de HTML, o JavaScript precisa de JSON (JavaScript Object Notation) e um aplicativo Java pode precisar de XML.

O terceiro princípio arquitetural do REST é que seus serviços devem ser orientados à representação. Cada serviço é endereçável através de um URI específico e as representações são trocadas entre o cliente e o serviço. 
Em um sistema RESTful, a complexidade da interação cliente-servidor está nas representações sendo transmitidas para frente e para trás. Essas representações podem ser XML, JSON, YAML ou realmente qualquer formato que você possa criar.
Com o HTTP, a representação é o corpo da mensagem da sua solicitação ou resposta. Um corpo da mensagem HTTP pode estar em qualquer formato que o servidor e o cliente desejem trocar. O HTTP usa o cabeçalho Content-Type para informar ao cliente ou servidor que formato de dados está recebendo. A cadeia de valor do cabeçalho Content-Type está no formato MIME (Multipurpose Internet Mail Extension).

O terceiro princípio arquitetural do REST é que seus serviços devem ser orientados à representação. Cada serviço é endereçável através de um URI específico e as representações são trocadas entre o cliente e o serviço. 
Em um sistema RESTful, a complexidade da interação cliente-servidor está nas representações sendo transmitidas para frente e para trás. Essas representações podem ser XML, JSON, YAML ou realmente qualquer formato que você possa criar.
Com o HTTP, a representação é o corpo da mensagem da sua solicitação ou resposta. Um corpo da mensagem HTTP pode estar em qualquer formato que o servidor e o cliente desejem trocar. O HTTP usa o cabeçalho Content-Type para informar ao cliente ou servidor que formato de dados está recebendo. A cadeia de valor do cabeçalho Content-Type está no formato MIME (Multipurpose Internet Mail Extension).

* **Communicate statelessly:**

O quarto princípio RESTful é a idéia de comunicação stateless. No REST, sem estado significa que não há dados da sessão do cliente armazenados no servidor. 
O servidor registra e gerencia apenas o estado dos recursos que expõe. 
Se precisar haver dados específicos da sessão, eles deverão ser mantidos e mantidos pelo cliente e transferidos para o servidor com cada solicitação, conforme necessário. 
Uma camada de serviço que não precisa manter as sessões do cliente é muito mais fácil de escalar, pois precisa fazer muito menos replicações caras em um ambiente em cluster. 
É muito mais fácil ampliar, porque tudo o que você precisa fazer é adicionar máquinas.

* **Hypermedia As The Engine Of Application State:**

O princípio final do REST é a idéia de usar a hipermídia como o mecanismo do estado do aplicativo (HATEOAS). A hipermídia é uma abordagem centrada em documentos, com suporte adicional para incorporar links a outros serviços e informações nesse formato de documento. 
Um dos usos da hipermídia e dos hiperlinks é compor conjuntos complexos de informações de fontes diferentes. As informações podem estar na intranet da empresa ou dispersas na Internet. Os hiperlinks nos permitem fazer referência e agregar dados adicionais sem inchar nossas respostas. O pedido de comércio eletrônico em endereçabilidade é um exemplo disso.

### Boas práticas de design de serviços

* **Domain-driven Design (DDD):**
“DDD é um conjunto de ferramentas que ajudam o processo de design e implementação de um software que têm o objetivo de entregar alto valor tanto estrategica quanto taticamente”
Vaughn Vernon - Implementing Domain-Driven Design
* **Domain-driven Design (DDD) – Referências 
* **Test-driven Development (TDD)
“Somente escreva código para corrigir um teste que falhou. Isso é desenvolvimento orientado a teste, ou TDD”
Lasse Koskela
Test Driven: Practical TDD and Acceptance TDD for Java Developers
* **Test-driven Development (TDD) – Referências 
* **Exponential Backoff
A falha nos sistemas distribuídos é inevitável. Em vez de tentar evitar totalmente a falha, queremos projetar sistemas capazes de auto-reparo. Para conseguir isso, é essencial ter uma boa estratégia para os clientes seguirem ao iniciar novas tentativas. Um serviço pode ficar temporariamente indisponível ou ter um problema que exija resposta manual de um engenheiro de plantão. Em qualquer cenário, os clientes devem poder enfileirar e repetir as solicitações para obter a melhor chance de sucesso.
Repetir incessantemente no caso de um erro não é uma tática eficaz. Se todos os clientes continuamente enfileiram novas tentativas sem nunca desistirem, você acabará com um problema massivo. À medida que a linha do tempo da falha progride, mais clientes terão falhas, resultando em mais tentativas. Você terminará com um padrão de tráfego (similar a um ataque DoS). 
* **Exponential Backoff – Referências 
* **Pattern: Service Registry
Contexto:
Clientes de um serviço usam o padrão Service Discovery para determiner a localização das instâncias de um serviço.
Problema:
Como os clientes de um serviço e/ou roteadores sabem sobre a disponibilidade de um serviço?
* **Pattern: Service Registry – Referências 
* **Pattern: Circuit Breaker
Contexto:
O padrão Microservices foi aplicado. Os serviços às vezes colaboram ao lidar com solicitações. Quando um serviço invoca sincronicamente outro, há sempre a possibilidade de que o outro serviço esteja indisponível ou apresente uma latência tão alta que é essencialmente inutilizável. Recursos preciosos, como threads, podem ser consumidos no chamador enquanto aguarda a resposta do outro serviço. Isso pode levar ao esgotamento de recursos, o que tornaria o serviço de chamador incapaz de lidar com outras requisições. A falha de um serviço pode potencialmente migrar para outros serviços em todo o sistema.
Problema:
Como evitar que uma falha de rede ou serviço caia em cascata para outros serviços?
* **Pattern: Circuit Breaker – Referências 
* **Pattern: Command Query Responsibility Segregation (CQRS)
Contexto:
O padrão de Microservices é utilizado bem como o padrão de Database per service. Como resultado, se tornou complexo executar consultas a dados de multiplos serviços. Além disso, se o padrão de Event sourcing, os dados não serão mais facilmente consultados.
Problema:
Como implementar uma consulta que recupera dados de multiplos serviços em uma arquitetura de microserviços?
Solução:
Definir uma base de dados de leitura, desenhada para suportar consultas de dados de multiplos serviços. Cada serviço mantém a replica atualizada subscrevendo um serviço de Domain Event para publicar seus dados.
* **Pattern: Command Query Responsibility Segregation (CQRS) - Referências
* **Pattern: Event sourcing
Contexto:
Um comando de serviço geralmente precisa atualizar o banco de dados e enviar mensagens/eventos. Por exemplo, um serviço que participa de uma Saga precisa atualizar atomicamente o banco de dados e enviar mensagens/eventos. Da mesma forma, um serviço que publica um evento de domínio deve atualizar atomicamente um agregado e publicar um evento. A atualização do banco de dados e o envio da mensagem devem ser atômicos para evitar inconsistências de dados e bugs. No entanto, não é viável usar uma transação distribuída que abranja o banco de dados e o intermediário de mensagens para atualizar atomicamente o banco de dados e publicar mensagens/eventos. 
Problema:
Como atualizar de forma confiável/atômica o banco de dados e publicar mensagens/eventos?
* **Pattern: Event sourcing
Solução:
O Event sourcing persiste o estado de uma entidade de negócio, como um Pedido de Compra ou um Cliente, como uma sequência de eventos que mudam de estado. Sempre que o estado de uma entidade de negócio é alterado, um novo evento é anexado à lista de eventos. Como salvar um evento é uma única operação, é inerentemente atômico. A aplicação reconstrói o estado atual de uma entidade reproduzindo os eventos.
Aplicaçõest persistem eventos em um armazenamento de eventos, que é um banco de dados de eventos. O armazenamento tem uma API para adicionar e recuperar os eventos de uma entidade e se comporta como um intermediário de mensagens. Ele fornece uma API que permite que os serviços assinem eventos. Quando um serviço salva um evento no armazenamento de eventos, ele é entregue a todos os assinantes interessados.
* **Pattern: Event sourcing – Referências 

