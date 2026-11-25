# PLANEJAMENTO TÉCNICO — MINI MARKETPLACE

**Versão:** 1.0  
**Status:** Proposta de planejamento  
**Metodologia:** Spec-Driven Development

## 1. Arquitetura

O sistema utilizará MVC com uma camada adicional de serviços.

- Model: representação das entidades.
- View: interfaces FXML e estilos CSS.
- Controller: gerenciamento de eventos e interações.
- Service: regras de negócio e operações.
- Util: funcionalidades auxiliares.

## 2. Estrutura do projeto

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
    │   │       │   ├── Produto.java
    │   │       │   ├── Categoria.java
    │   │       │   ├── Carrinho.java
    │   │       │   └── ItemCarrinho.java
    │   │       ├── controller/
    │   │       │   ├── CatalogoController.java
    │   │       │   ├── ProdutoController.java
    │   │       │   ├── CarrinhoController.java
    │   │       │   └── ProdutoCardController.java
    │   │       ├── service/
    │   │       │   ├── ProdutoService.java
    │   │       │   └── CarrinhoService.java
    │   │       └── util/
    │   │           └── NavegacaoUtil.java
    │   └── resources/
    │       └── br/com/marketplace/
    │           ├── view/
    │           │   ├── catalogo.fxml
    │           │   ├── produto.fxml
    │           │   ├── carrinho.fxml
    │           │   └── produto-card.fxml
    │           ├── css/
    │           │   └── style.css
    │           └── images/
    │               └── produtos/
    └── test/
        └── java/
            └── br/com/marketplace/
                ├── model/
                └── service/
```

## 3. Classes e responsabilidades

| Classe | Responsabilidade |
|---|---|
| Produto | Dados de um produto. |
| Categoria | Dados de uma categoria. |
| Carrinho | Representação do carrinho. |
| ItemCarrinho | Produto e quantidade. |
| ProdutoService | Catálogo, pesquisa e filtros. |
| CarrinhoService | Gerenciamento do carrinho. |
| CatalogoController | Catálogo, pesquisa, categorias e carrossel. |
| ProdutoController | Detalhes do produto. |
| CarrinhoController | Interface do carrinho. |
| ProdutoCardController | Apresentação reutilizável de produtos. |
| NavegacaoUtil | Navegação entre interfaces. |

## 4. Interfaces

- catalogo.fxml: catálogo, pesquisa, categorias e carrossel.
- produto.fxml: detalhes do produto.
- carrinho.fxml: gerenciamento do carrinho.
- produto-card.fxml: componente reutilizável.

## 5. Tecnologias e dependências

- Java 21 LTS.
- JavaFX 21.
- Maven.
- FXML.
- CSS.
- JUnit 5.
- Git.

Dependências principais: javafx-controls, javafx-fxml e junit-jupiter.

## 6. Dados iniciais

- 12 produtos.
- 4 categorias.
- 3 produtos por categoria.
- Imagens locais.
- Produtos destacados identificados por atributo booleano.

## 7. Carrossel

- Três produtos simultaneamente.
- Navegação manual.
- Navegação circular.
- Apenas produtos em destaque.
- Sem reprodução automática.
- Não duplicar produtos quando houver menos de três destaques.

## 8. Navegação

O sistema terá três telas principais: catálogo, detalhes e carrinho.

Os serviços serão compartilhados durante a execução para preservar o estado dos produtos e do carrinho.

## 9. Fases de implementação

P01 — Configuração do ambiente e Maven.

P02 — Modelagem das classes.

P03 — Dados fictícios e serviços.

P04 — Interface do catálogo e carrossel.

P05 — Navegação e detalhes.

P06 — Carrinho e cálculos.

P07 — Integração, estilização e testes.

## 10. Estratégia de testes

Utilizar JUnit 5 para testar modelos e serviços.

Executar testes manuais para verificar navegação, filtros, carrossel, carrinho e interface.

## 11. Governança

Todas as implementações deverão respeitar a Constituição e a Especificação aprovadas.

Alterações estruturais deverão ser justificadas e documentadas.

**Fim do documento de planejamento — Versão 1.0.**
