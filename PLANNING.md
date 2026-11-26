# PLANNING.md — Mini Marketplace

**Versão:** 1.2  
**Status:** Aprovado  
**Etapa SDD:** 3 — Planejamento

## 1. Objetivo

Definir a arquitetura, a organização dos arquivos, as tecnologias, as etapas de desenvolvimento e a estratégia de testes do Mini Marketplace.

O desenvolvimento seguirá a Constituição e a Especificação aprovadas, sem adicionar funcionalidades fora do escopo definido.

## 2. Ambiente de desenvolvimento

- **IDE:** Visual Studio Code (VS Code).
- **Linguagem:** Java 21 LTS.
- **Interface:** JavaFX 21.
- **Estrutura visual:** FXML.
- **Estilização:** CSS.
- **Gerenciamento:** Maven.
- **Testes:** JUnit 5.
- **Versionamento:** Git.

## 3. Arquitetura

Será utilizada a arquitetura **MVC (Model-View-Controller)** com uma camada adicional de serviços.

### Model
Responsável por representar os dados e as entidades do sistema.

Classes:
- `Produto.java`
- `Categoria.java`
- `Carrinho.java`
- `ItemCarrinho.java`

### View
Responsável pela estrutura visual das telas, utilizando FXML e CSS.

Arquivos:
- `catalogo.fxml`
- `produto.fxml`
- `carrinho.fxml`
- `produto-card.fxml`

### Controller
Responsável por receber as interações do usuário e atualizar as interfaces.

Classes:
- `CatalogoController.java`
- `ProdutoController.java`
- `CarrinhoController.java`
- `ProdutoCardController.java`

### Service
Responsável pelas regras de negócio e operações do sistema.

Classes:
- `ProdutoService.java`: disponibilização dos produtos, pesquisa e filtros.
- `CarrinhoService.java`: adição, remoção, alteração de quantidades e cálculos.

### Utilitário
- `NavegacaoUtil.java`: centralização da navegação e carregamento das telas.

O fluxo principal será:

Usuário → View → Controller → Service → Model.

Os serviços serão compartilhados durante a execução para preservar os dados do carrinho e o estado necessário entre as telas.

## 4. Estrutura de diretórios

```text
mini-marketplace/
├── pom.xml
├── README.md
├── CONSTITUTION.md
├── SPECIFICATION.md
├── PLANNING.md
└── src/
    ├── main/
    │   ├── java/
    │   │   └── br/com/marketplace/
    │   │       ├── Main.java
    │   │       ├── model/
    │   │       ├── controller/
    │   │       ├── service/
    │   │       └── util/
    │   └── resources/
    │       └── br/com/marketplace/
    │           ├── view/
    │           ├── css/
    │           └── images/
    │               └── produtos/
    └── test/
        └── java/
            └── br/com/marketplace/
                ├── model/
                └── service/
```

O componente `produto-card.fxml`, acompanhado de seu controller, será reutilizado no catálogo e no carrossel.

A configuração de acesso reflexivo do FXML será considerada caso o projeto utilize módulos Java, por meio de `module-info.java`.

## 5. Organização das telas

### Catálogo
- Cabeçalho com identificação do marketplace e acesso ao carrinho.
- Pesquisa em tempo real.
- Filtros por categoria.
- Carrossel manual e circular com até três produtos em destaque.
- Grade com até três produtos por linha.
- Cards reutilizáveis com imagem, nome, categoria, preço e ações.

### Detalhes do produto
- Imagem ampliada.
- Nome, categoria, descrição e preço.
- Botão para adicionar ao carrinho.
- Opção para retornar ao catálogo.

### Carrinho
- Lista de produtos adicionados.
- Preço unitário e quantidade.
- Controles para aumentar ou diminuir quantidades.
- Subtotal por produto.
- Opção de remover produtos.
- Total da compra.
- Mensagem para carrinho vazio.

Não haverá checkout ou pagamento.

## 6. Regras de implementação

- Os 12 produtos fictícios serão disponibilizados em memória.
- Os produtos serão distribuídos igualmente entre as quatro categorias.
- Valores monetários utilizarão `BigDecimal`.
- A pesquisa ignorará diferenças entre letras maiúsculas e minúsculas.
- Pesquisa e filtro de categoria funcionarão simultaneamente.
- Adicionar um produto já existente no carrinho incrementará sua quantidade.
- O indicador do carrinho mostrará a quantidade total de unidades.
- A navegação preservará os serviços compartilhados e o estado necessário.
- Os filtros e a pesquisa deverão permanecer preservados ao retornar ao catálogo durante a execução.

## 7. Etapas de desenvolvimento

A implementação será realizada por **módulos completos**, com explicação de cada módulo antes de avançar para o próximo.

| Etapa | Módulo | Objetivo |
|---|---|---|
| P01 | Configuração inicial | Criar projeto Maven, configurar JavaFX e abrir a janela principal. |
| P02 | Modelagem | Implementar Produto, Categoria, Carrinho e ItemCarrinho. |
| P03 | Serviços e dados | Criar ProdutoService, CarrinhoService e os 12 produtos fictícios. |
| P04 | Catálogo | Implementar interface, cards, pesquisa, filtros e carrossel. |
| P05 | Detalhes e navegação | Implementar tela de detalhes e navegação entre telas. |
| P06 | Carrinho | Implementar listagem, quantidades, remoção, subtotais e total. |
| P07 | Integração e finalização | Integrar módulos, revisar CSS, validar requisitos e executar testes. |

Cada módulo será entregue com:
- Objetivo e relação com os documentos SDD.
- Arquivos que serão criados ou modificados.
- Código completo dos arquivos envolvidos.
- Explicação das principais decisões e responsabilidades.
- Instruções para executar e validar o módulo.
- Verificação dos requisitos relacionados antes de avançar.

Não será necessário acompanhar a implementação linha por linha, mas cada módulo será explicado para permitir compreensão e acompanhamento do projeto.

## 8. Estratégia de testes

### Testes automatizados
Utilizar JUnit 5 para verificar:
- Validações das entidades.
- Cálculo de subtotais e total.
- Adição e remoção de produtos.
- Alteração de quantidades.
- Pesquisa e filtros.
- Regras de negócio do carrinho.

### Testes manuais
Verificar:
- Carregamento das telas FXML.
- Navegação entre catálogo, detalhes e carrinho.
- Funcionamento do carrossel circular.
- Pesquisa e filtros simultâneos.
- Atualização do indicador do carrinho.
- Atualização dos valores após alterações.
- Comportamento do carrinho vazio.
- Adaptação da interface ao redimensionamento da janela.

## 9. Riscos e medidas preventivas

| Risco | Medida preventiva |
|---|---|
| Erros de carregamento FXML | Implementar e testar as telas gradualmente. |
| Dificuldade na navegação | Centralizar o carregamento das telas. |
| Código duplicado nos cards | Criar componente reutilizável. |
| Erros em cálculos monetários | Utilizar BigDecimal. |
| Perda do estado do carrinho | Compartilhar instâncias dos serviços. |
| Problemas com imagens | Utilizar caminhos locais em resources. |
| Acoplamento entre classes | Respeitar as responsabilidades de cada camada. |

## 10. Controle de escopo

Durante a implementação, serão respeitados os limites definidos na Constituição e na Especificação.

Qualquer alteração relevante na arquitetura, nos requisitos ou nas funcionalidades deverá ser apresentada e aprovada antes de ser incorporada.

Funcionalidades como login, banco de dados, pagamento, frete e gerenciamento administrativo continuam fora do escopo.

## 11. Critério de conclusão

A etapa de implementação será considerada concluída quando:
- Os módulos estiverem integrados.
- Os requisitos funcionais RF01 a RF10 estiverem atendidos.
- Os testes previstos tiverem sido executados.
- A interface estiver consistente com a identidade visual definida.
- O projeto puder ser executado pelo ambiente configurado.

---

**Decisões aprovadas para a implementação:**

- IDE: Visual Studio Code.
- Desenvolvimento: módulos completos, explicados individualmente.
- Arquitetura: MVC + Service.
- Tecnologias: Java 21, JavaFX 21, FXML, CSS, Maven e JUnit 5.

**Status da Etapa 3: CONCLUÍDA E APROVADA.**
