# CONSTITUIÇÃO DO PROJETO — MINI MARKETPLACE

**Versão:** 1.1  
**Status:** Validada  
**Disciplina:** Programação Orientada a Objetos  
**Tecnologia principal:** Java 21 + JavaFX 21  
**Metodologia:** Spec-Driven Development (SDD)

---

## 1. Propósito do projeto

Desenvolver uma aplicação desktop de Marketplace utilizando JavaFX, com uma interface gráfica moderna, intuitiva e parcialmente interativa.

O sistema deverá simular um ambiente de comércio eletrônico, permitindo visualizar produtos, navegar por categorias, pesquisar itens e gerenciar um carrinho de compras.

O desenvolvimento deverá priorizar a aplicação prática dos conceitos de Programação Orientada a Objetos, mantendo uma implementação simples, organizada e adequada ao contexto acadêmico.

## 2. Princípios fundamentais

### 2.1. Simplicidade acima da complexidade
- Implementar somente funcionalidades necessárias para os objetivos do projeto.
- Evitar recursos avançados que não agreguem valor acadêmico ou funcional.
- Priorizar soluções simples, legíveis e fáceis de manter.
- Não implementar funcionalidades completas de um e-commerce comercial.

### 2.2. Orientação a objetos
- Aplicar encapsulamento, abstração, herança e polimorfismo quando fizer sentido.
- Representar entidades do sistema por meio de classes e objetos.
- Evitar concentrar regras de negócio diretamente nas classes responsáveis pela interface.
- Priorizar alta coesão e baixo acoplamento.
- Utilizar coleções Java para armazenar e manipular os dados.

### 2.3. Organização e manutenção
- Separar responsabilidades entre modelos, controladores e interface.
- Evitar duplicação de código.
- Utilizar nomes descritivos para classes, métodos e atributos.
- Manter uma estrutura de diretórios organizada.
- Desenvolver funcionalidades de maneira incremental.

### 2.4. Experiência do usuário
- A interface deverá ser intuitiva, moderna e visualmente consistente.
- Os elementos deverão apresentar hierarquia visual clara.
- As ações do usuário deverão fornecer feedback adequado.
- Evitar telas excessivamente carregadas.
- Garantir dimensões, espaçamentos e alinhamentos coerentes.

## 3. Identidade visual

### 3.1. Tema visual: Mixed

A aplicação utilizará uma identidade visual mista, combinando elementos claros e escuros.

Diretrizes:
- Fundo predominantemente claro nas áreas de conteúdo.
- Elementos escuros em áreas de destaque, como cabeçalho ou navegação lateral.
- Cores de destaque para botões, preços e elementos interativos.
- Cards de produtos com aparência limpa e organizada.
- Bordas suaves e espaçamentos consistentes.
- Tipografia legível e moderna.

### 3.2. Componentes visuais
- Cabeçalho com identificação do Marketplace e acesso ao carrinho.
- Barra de pesquisa.
- Navegação por categorias.
- Carrossel de produtos em destaque.
- Cards individuais de produtos.
- Área de visualização do carrinho.

### 3.3. Padronização
- Toda a estilização deverá ser centralizada em arquivos CSS sempre que possível.
- Componentes semelhantes deverão compartilhar padrões visuais.
- Evitar estilos inline desnecessários no FXML.

## 4. Pilha tecnológica

| Tecnologia | Utilização |
|---|---|
| Java 21 LTS | Linguagem principal |
| JavaFX 21 | Interface gráfica |
| FXML | Estruturação declarativa das interfaces |
| CSS | Estilização dos componentes |
| Maven | Gerenciamento de dependências |
| JUnit 5 | Testes unitários |
| Git | Controle de versão |

### Restrições tecnológicas
- Não utilizar frameworks adicionais sem justificativa.
- Não utilizar tecnologias web para construir a interface principal.
- Não utilizar bibliotecas externas quando os recursos nativos do JavaFX forem suficientes.
- Não implementar APIs ou serviços externos nesta versão.
- Não utilizar banco de dados inicialmente.
- Os dados serão simulados e armazenados em memória.

## 5. Arquitetura e padrões de desenvolvimento

O projeto utilizará uma arquitetura baseada no padrão MVC (Model-View-Controller).

**Model:** representação dos dados e regras de negócio.

**View:** interfaces desenvolvidas em FXML e estilizadas com CSS.

**Controller:** responsável por receber as interações do usuário e conectar a interface às regras de negócio.

### Estrutura inicial

    src/
    ├── main/
    │   ├── java/
    │   │   └── br/com/marketplace/
    │   │       ├── Main.java
    │   │       ├── model/
    │   │       ├── controller/
    │   │       ├── service/
    │   │       └── util/
    │   │
    │   └── resources/
    │       ├── view/
    │       ├── css/
    │       └── images/
    │
    └── test/
        └── java/

A estrutura poderá evoluir conforme a necessidade, preservando a separação de responsabilidades.

## 6. Padrões de codificação

### 6.1. Convenções de nomenclatura
- Classes: PascalCase. Exemplo: ProdutoController.
- Métodos: camelCase. Exemplo: adicionarProduto().
- Variáveis: camelCase. Exemplo: precoProduto.
- Constantes: MAIUSCULAS_COM_UNDERSCORE.
- Pacotes: letras minúsculas.

### 6.2. Qualidade do código
- Utilizar atributos privados sempre que apropriado.
- Disponibilizar métodos públicos somente quando necessários.
- Evitar métodos excessivamente extensos.
- Evitar duplicação de código.
- Utilizar constantes para valores compartilhados.
- Documentar regras de negócio não óbvias.
- Tratar exceções adequadamente.
- Não utilizar comentários para explicar código evidente.

### 6.3. Boas práticas de POO
- Não utilizar herança apenas para demonstrar o conceito.
- Utilizar composição quando representar melhor a relação entre objetos.
- Evitar classes com múltiplas responsabilidades.
- Centralizar regras de negócio em classes apropriadas.

## 7. Escopo funcional

O projeto terá escopo básico, contemplando:

1. Exibição dos produtos disponíveis.
2. Carrossel de produtos em destaque.
3. Navegação por categorias.
4. Pesquisa de produtos pelo nome.
5. Visualização das informações dos produtos nos cards.
6. Adição e remoção de produtos do carrinho.
7. Alteração da quantidade de itens.
8. Cálculo automático do subtotal e total.

### Funcionalidades fora do escopo inicial
- Autenticação real de usuários.
- Cadastro de vendedores.
- Pagamentos.
- Integração com transportadoras.
- Banco de dados.
- Integrações com APIs externas.
- Sistema completo de pedidos.

Funcionalidades adicionais somente poderão ser consideradas mediante revisão do escopo.

## 8. Regras de interface JavaFX

- Utilizar FXML para estruturar as telas.
- Utilizar CSS para personalização visual.
- Utilizar controladores Java para gerenciar eventos e interações.
- Utilizar componentes nativos do JavaFX.
- Criar componentes reutilizáveis quando necessário.
- Implementar o carrossel com navegação entre produtos.
- Fornecer feedback visual para as ações do usuário.
- Evitar bloquear a JavaFX Application Thread durante operações demoradas.
- Manter consistência visual entre as diferentes telas.

## 9. Regras de segurança

- Não armazenar senhas reais ou informações pessoais sensíveis.
- Não incluir credenciais, tokens ou chaves privadas no código.
- Validar os dados informados pelo usuário.
- Impedir quantidades negativas ou inválidas no carrinho.
- Verificar a existência de produtos antes de executar operações.
- Não permitir preços negativos.
- Tratar erros sem encerrar inesperadamente a aplicação.
- Não executar comandos externos ou operações desnecessárias no sistema operacional.

## 10. Regras para utilização de IA

Todas as operações de IA deverão respeitar esta Constituição.

A IA deverá:
- Consultar as especificações existentes antes de propor alterações.
- Não implementar funcionalidades não solicitadas ou aprovadas.
- Não modificar decisões arquiteturais sem justificativa e autorização.
- Priorizar códigos simples e compatíveis com a pilha tecnológica.
- Explicar decisões técnicas relevantes.
- Evitar dependências desnecessárias.
- Preservar funcionalidades existentes durante alterações.
- Informar quando uma solicitação entrar em conflito com algum princípio estabelecido.
- Não afirmar que funcionalidades foram testadas sem verificar sua execução.

## 11. Governança da Constituição

Esta Constituição será a referência principal do projeto durante todas as etapas do SDD.

Suas regras deverão permanecer válidas durante o desenvolvimento.

Alterações somente deverão ocorrer quando:
- Houver necessidade técnica justificada.
- Uma decisão anterior se mostrar inadequada.
- O escopo for formalmente atualizado.

Toda alteração deverá ser documentada, versionada e aprovada antes de ser aplicada.

---

## 12. Princípio final

Desenvolver um Marketplace desktop funcional, visualmente agradável e academicamente consistente, priorizando a aplicação dos conceitos de Programação Orientada a Objetos, sem comprometer a simplicidade e a organização do projeto.
