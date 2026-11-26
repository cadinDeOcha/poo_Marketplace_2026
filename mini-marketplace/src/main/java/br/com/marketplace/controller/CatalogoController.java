package br.com.marketplace.controller;

import br.com.marketplace.model.Categoria;
import br.com.marketplace.model.Produto;
import br.com.marketplace.service.CarrinhoService;
import br.com.marketplace.service.ProdutoService;
import br.com.marketplace.util.NavegacaoUtil;
import br.com.marketplace.util.AlertaUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.beans.binding.Bindings;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatalogoController {

    private static final Logger LOGGER = Logger.getLogger(CatalogoController.class.getName());

    private static final int COLUNAS_CATALOGO = 3;
    private static final int DESTAQUES_VISIVEIS = 3;

    private final ProdutoService produtoService;
    private final CarrinhoService carrinhoService;

    @FXML
    private TextField campoPesquisa;

    @FXML
    private GridPane painelProdutos;

    @FXML
    private GridPane painelDestaques;

    @FXML
    private Button lblQuantidadeCarrinho;

    @FXML
    private Label lblQuantidadeProdutos;

    @FXML
    private Label lblEstadoProdutos;

    @FXML
    private Button btnTodos;

    @FXML
    private Button btnEletronicos;

    @FXML
    private Button btnInformatica;

    @FXML
    private Button btnGames;

    @FXML
    private Button btnAcessorios;

    private Categoria categoriaSelecionada;

    private List<Produto> produtosDestaque;

    private int indiceCarrossel = 0;

    public CatalogoController(
            ProdutoService produtoService,
            CarrinhoService carrinhoService) {

        this.produtoService = produtoService;
        this.carrinhoService = carrinhoService;
    }

    @FXML
    public void initialize() {

        categoriaSelecionada = null;

        produtosDestaque = produtoService.listarDestaques();

        campoPesquisa.textProperty().addListener(
                (observable, antigoValor, novoValor) -> {
                    atualizarCatalogo();
                });

        atualizarCatalogo();
        atualizarCarrossel();
        atualizarIndicadorCarrinho();
        atualizarBotaoCategoria();
    }

    // =========================
    // CATÁLOGO E PESQUISA
    // =========================

    private void atualizarCatalogo() {

        painelProdutos.getChildren().clear();

        String termo = campoPesquisa.getText();

        List<Produto> produtos = produtoService.buscarProdutos(
                termo,
                categoriaSelecionada);

        lblQuantidadeProdutos.setText(
                produtos.size() +
                        (produtos.size() == 1
                                ? " produto encontrado"
                                : " produtos encontrados"));

        lblEstadoProdutos.setVisible(produtos.isEmpty());
        lblEstadoProdutos.setManaged(produtos.isEmpty());

        for (int i = 0; i < produtos.size(); i++) {

            int coluna = i % COLUNAS_CATALOGO;
            int linha = i / COLUNAS_CATALOGO;

            painelProdutos.add(
                    criarCard(produtos.get(i)),
                    coluna,
                    linha);
        }
    }

    // =========================
    // FILTROS DE CATEGORIA
    // =========================

    @FXML
    private void filtrarTodos() {
        categoriaSelecionada = null;
        atualizarBotaoCategoria();
        atualizarCatalogo();
    }

    @FXML
    private void filtrarEletronicos() {
        categoriaSelecionada = Categoria.ELETRONICOS;
        atualizarBotaoCategoria();
        atualizarCatalogo();
    }

    @FXML
    private void filtrarInformatica() {
        categoriaSelecionada = Categoria.INFORMATICA;
        atualizarBotaoCategoria();
        atualizarCatalogo();
    }

    @FXML
    private void filtrarGames() {
        categoriaSelecionada = Categoria.GAMES;
        atualizarBotaoCategoria();
        atualizarCatalogo();
    }

    @FXML
    private void filtrarAcessorios() {
        categoriaSelecionada = Categoria.ACESSORIOS;
        atualizarBotaoCategoria();
        atualizarCatalogo();
    }

    private void atualizarBotaoCategoria() {

        Button[] botoes = {
                btnTodos,
                btnEletronicos,
                btnInformatica,
                btnGames,
                btnAcessorios
        };

        for (Button botao : botoes) {
            botao.getStyleClass().remove("categoria-selecionada");
        }

        Button selecionado;

        if (categoriaSelecionada == null) {
            selecionado = btnTodos;
        } else {
            selecionado = switch (categoriaSelecionada) {
                case ELETRONICOS -> btnEletronicos;
                case INFORMATICA -> btnInformatica;
                case GAMES -> btnGames;
                case ACESSORIOS -> btnAcessorios;
            };
        }

        if (!selecionado.getStyleClass().contains(
                "categoria-selecionada")) {

            selecionado.getStyleClass().add(
                    "categoria-selecionada");
        }
    }

    // =========================
    // CARROSSEL DE DESTAQUES
    // =========================

    @FXML
    private void proximoDestaque() {

        if (produtosDestaque.isEmpty()) {
            return;
        }

        indiceCarrossel = (indiceCarrossel + 1) % produtosDestaque.size();

        atualizarCarrossel();
    }

    @FXML
    private void destaqueAnterior() {

        if (produtosDestaque.isEmpty()) {
            return;
        }

        indiceCarrossel = (indiceCarrossel - 1 + produtosDestaque.size())
                % produtosDestaque.size();

        atualizarCarrossel();
    }

    private void atualizarCarrossel() {

        painelDestaques.getChildren().clear();

        if (produtosDestaque.isEmpty()) {
            return;
        }

        int quantidade = Math.min(
                DESTAQUES_VISIVEIS,
                produtosDestaque.size());

        for (int i = 0; i < quantidade; i++) {

            int indice = (indiceCarrossel + i)
                    % produtosDestaque.size();

            Produto produto = produtosDestaque.get(indice);

            painelDestaques.add(criarCard(produto), i, 0);
        }
    }

    // =============================
    // ACESSO AO CARRINHO
    // =============================

    @FXML
    private void abrirCarrinho() {

        try {

            NavegacaoUtil.abrirCarrinho();

        } catch (IOException e) {

            AlertaUtil.exibirErro(
                    "Erro de navegação",
                    "Não foi possível abrir o carrinho.",
                    e.getMessage());

            LOGGER.log(
                    Level.SEVERE,
                    "Não foi possível abrir o carrinho.",
                    e);
        }
    }

    // =========================
    // CRIAÇÃO DOS CARDS
    // =========================

    private Node criarCard(Produto produto) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/br/com/marketplace/view/produto-card.fxml"));

            Region card = loader.load();
            // A imagem acompanha a largura do card, sem alterar a proporção.
            StackPane container = (StackPane) loader.getNamespace().get("containerImagem");
            ImageView imagem = (ImageView) loader.getNamespace().get("imagemProduto");
            imagem.fitWidthProperty().bind(Bindings.max(0, container.widthProperty().subtract(12)));

            ProdutoCardController controller = loader.getController();

            controller.configurar(
                    produto,
                    this::adicionarProduto);

            return card;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Erro ao carregar o card do produto: "
                            + produto.getNome(),
                    e);
        }
    }

    // =========================
    // CARRINHO
    // =========================

    private void adicionarProduto(Produto produto) {

        carrinhoService.adicionarProduto(produto);

        atualizarIndicadorCarrinho();
    }

    public void atualizarIndicadorCarrinho() {

        lblQuantidadeCarrinho.setText(
                "Carrinho (" +
                        carrinhoService.calcularQuantidadeTotal() +
                        ")");
    }
}
