# ESPECIFICAÇÃO DO PROJETO — MINI MARKETPLACE

**Versão:** 1.1  
**Status:** Aprovada  
**Metodologia:** Spec-Driven Development  
**Disciplina:** Programação Orientada a Objetos

## 1. Visão geral

Desenvolver uma aplicação desktop de Marketplace utilizando Java 21 e JavaFX 21, com interfaces estruturadas em FXML e estilizadas em CSS.

O sistema permitirá visualizar produtos, navegar por categorias, pesquisar itens e gerenciar um carrinho de compras.

A aplicação utilizará dados fictícios armazenados em memória.

## 2. Requisitos funcionais

| Código | Requisito |
|---|---|
| RF01 | Exibir produtos disponíveis no catálogo. |
| RF02 | Apresentar carrossel de produtos em destaque. |
| RF03 | Filtrar produtos por categoria. |
| RF04 | Pesquisar produtos pelo nome. |
| RF05 | Apresentar uma tela separada com os detalhes do produto. |
| RF06 | Adicionar produtos ao carrinho. |
| RF07 | Remover produtos do carrinho. |
| RF08 | Alterar a quantidade dos produtos. |
| RF09 | Calcular subtotal por produto. |
| RF10 | Calcular o valor total do carrinho. |

## 3. Telas da aplicação

### 3.1. Catálogo
- Cabeçalho com identificação do Marketplace.
- Barra de pesquisa em tempo real.
- Indicador de quantidade de itens no carrinho.
- Navegação por categorias.
- Carrossel de produtos em destaque.
- Catálogo organizado em grade.
- Cards com imagem, nome, categoria e preço.
- Botões para visualizar detalhes e adicionar ao carrinho.

### 3.2. Detalhes do produto
- Tela independente do catálogo.
- Imagem ampliada.
- Nome, categoria e descrição.
- Preço.
- Botão para adicionar ao carrinho.
- Botão para retornar ao catálogo.

### 3.3. Carrinho
- Lista de produtos adicionados.
- Imagem, nome e preço unitário.
- Controles de quantidade.
- Subtotal individual.
- Botão para remover produtos.
- Total da compra.
- Mensagem para carrinho vazio.
- Botão para retornar ao catálogo.

## 4. Especificações do carrossel

O carrossel deverá:
- Apresentar três produtos simultaneamente.
- Exibir somente produtos identificados como destaque.
- Possuir botões de navegação anterior e próximo.
- Utilizar navegação circular.
- Permitir retornar ao primeiro produto após alcançar o último.
- Atualizar os produtos exibidos sem exigir reinicialização da aplicação.

A navegação será manual, sem troca automática.

## 5. Especificações do catálogo

- O catálogo será organizado em grade com até três produtos por linha.
- A quantidade de linhas dependerá do número de produtos encontrados.
- Os cards deverão manter dimensões e identidade visual consistentes.
- A pesquisa deverá funcionar em tempo real.
- A pesquisa deverá ignorar diferenças entre letras maiúsculas e minúsculas.
- A categoria e a pesquisa deverão funcionar simultaneamente.
- A seleção de "Todos" deverá remover o filtro de categoria.

## 6. Regras de negócio

1. Cada produto deverá possuir identificador único.
2. Produtos deverão possuir nome, categoria e preço válido.
3. Preços não poderão ser negativos.
4. A quantidade de cada item deverá ser maior que zero.
5. Adicionar novamente um produto deverá incrementar sua quantidade.
6. Remover um produto deverá excluir seu item do carrinho.
7. Subtotal = preço unitário × quantidade.
8. Total = soma dos subtotais.
9. O carrinho poderá permanecer vazio.
10. A pesquisa não diferenciará letras maiúsculas e minúsculas.
11. Filtros de pesquisa e categoria deverão ser combinados.
12. Os dados serão carregados em memória.

Valores monetários deverão utilizar BigDecimal.

## 7. Requisitos não funcionais

- Interface intuitiva e visualmente consistente.
- Tema visual misto (áreas claras e elementos escuros).
- Código organizado conforme MVC.
- Interfaces estruturadas em FXML.
- Estilização centralizada em CSS.
- Componentes reutilizáveis.
- Compatibilidade com Java 21.
- Validação dos dados.
- Redimensionamento adequado da interface.
- Operações locais sem atrasos perceptíveis.

## 8. Modelo conceitual

Entidades principais:
- Produto
- Categoria
- Carrinho
- ItemCarrinho

Relacionamentos:
- Uma categoria poderá possuir vários produtos.
- Um carrinho poderá possuir vários itens.
- Cada item do carrinho estará associado a um produto.

## 9. Dados iniciais

A aplicação deverá iniciar com:
- 12 produtos fictícios.
- 4 categorias.
- Produtos previamente identificados para o carrossel de destaques.
- Imagens armazenadas localmente.

## 10. Critérios de aceitação

A aplicação será considerada funcional quando:
- Apresentar os produtos cadastrados.
- Permitir navegação circular no carrossel.
- Filtrar produtos por categoria.
- Pesquisar produtos em tempo real.
- Combinar pesquisa e categoria.
- Abrir uma tela independente de detalhes.
- Adicionar e remover produtos do carrinho.
- Alterar quantidades.
- Atualizar subtotais e total automaticamente.
- Apresentar corretamente o estado de carrinho vazio.

## 11. Limitações

Não serão implementados:
- Login e autenticação.
- Cadastro de vendedores.
- Pagamentos.
- Integração com transportadoras.
- Banco de dados.
- APIs externas.
- Gerenciamento de estoque.
- Cadastro administrativo de produtos.
- Avaliações e comentários.

## 12. Parâmetros definitivos

| Elemento | Definição |
|---|---|
| Linguagem | Java 21 |
| Interface | JavaFX 21 |
| Estrutura visual | FXML |
| Estilização | CSS |
| Arquitetura | MVC |
| Produtos iniciais | 12 |
| Categorias | 4 |
| Produtos no carrossel | 3 simultaneamente |
| Carrossel | Circular e manual |
| Catálogo | Até 3 produtos por linha |
| Detalhes | Tela separada |
| Carrinho | Tela separada |
| Moeda | Real brasileiro |
| Valores monetários | BigDecimal |
| Armazenamento | Memória |

**Status final:** Especificação aprovada e concluída.
