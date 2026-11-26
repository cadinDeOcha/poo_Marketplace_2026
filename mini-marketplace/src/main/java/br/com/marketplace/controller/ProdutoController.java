package br.com.marketplace.controller;

import br.com.marketplace.model.Produto;
import br.com.marketplace.service.CarrinhoService;
import br.com.marketplace.util.NavegacaoUtil;
import br.com.marketplace.util.AlertaUtil;
import br.com.marketplace.util.ImagemUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoController {

    private static final Logger LOGGER = Logger.getLogger(ProdutoController.class.getName());

    private final Produto produto;

    private final CarrinhoService carrinhoService;

    private final NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(
            Locale.of("pt", "BR"));

    @FXML
    private ImageView imagemProduto;

    @FXML
    private Label lblImagemIndisponivel;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblCategoria;

    @FXML
    private Label lblPreco;

    @FXML
    private Label lblDescricao;

    @FXML
    private Button lblQuantidadeCarrinho;

    @FXML
    private Label lblMensagem;

    public ProdutoController(
            Produto produto,
            CarrinhoService carrinhoService) {

        this.produto = produto;
        this.carrinhoService = carrinhoService;
    }

    // =============================
    // INICIALIZAÇÃO
    // =============================

    @FXML
    private void initialize() {

        carregarProduto();

        atualizarIndicadorCarrinho();

        lblMensagem.setText("");
    }

    // =============================
    // INFORMAÇÕES DO PRODUTO
    // =============================

    private void carregarProduto() {

        lblNome.setText(produto.getNome());

        lblCategoria.setText(
                produto.getCategoria().getNomeExibicao());

        lblPreco.setText(
                formatoMoeda.format(produto.getPreco()));

        lblDescricao.setText(produto.getDescricao());

        carregarImagem(produto.getCaminhoImagem());
    }

    // =============================
    // IMAGEM DO PRODUTO
    // =============================

    private void carregarImagem(String caminhoImagem) {

        ImagemUtil.carregarImagem(
                caminhoImagem,
                imagemProduto,
                lblImagemIndisponivel);
    }

    // =============================
    // CARRINHO
    // =============================

    @FXML
    private void adicionarAoCarrinho() {

        carrinhoService.adicionarProduto(produto);

        atualizarIndicadorCarrinho();

        lblMensagem.setText(
                "Produto adicionado ao carrinho!");

        System.out.println(
                produto.getNome() + " adicionado ao carrinho.");
    }

    private void atualizarIndicadorCarrinho() {

        lblQuantidadeCarrinho.setText(
                "Carrinho (" +
                        carrinhoService.calcularQuantidadeTotal() +
                        ")");
    }

    // =============================
    // ACESSO AO CARRINHO
    // =============================

    @FXML
    private void abrirCarrinho() {

        try {

            NavegacaoUtil.abrirCarrinho();

        } catch (IOException e) {

            LOGGER.log(
                    Level.SEVERE,
                    "Não foi possível abrir o carrinho.",
                    e);

            AlertaUtil.exibirErro(
                    "Erro de navegação",
                    "Não foi possível abrir o carrinho.",
                    e.getMessage());
        }
    }

    // =============================
    // NAVEGAÇÃO
    // =============================

    @FXML
    private void voltarAoCatalogo() {

        try {

            NavegacaoUtil.abrirCatalogo();

        } catch (IOException e) {

            LOGGER.log(
                    Level.SEVERE,
                    "Não foi possível abrir o catálogo.",
                    e);

            AlertaUtil.exibirErro(
                    "Erro de navegação",
                    "Não foi possível abrir o catálogo.",
                    e.getMessage());
        }
    }
}
