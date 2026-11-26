package br.com.marketplace.controller;

import br.com.marketplace.model.Produto;

import br.com.marketplace.util.NavegacaoUtil;

import br.com.marketplace.util.AlertaUtil;

import br.com.marketplace.util.ImagemUtil;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoCardController {

    private static final Logger LOGGER = Logger.getLogger(ProdutoCardController.class.getName());

    @FXML
    private Label lblNome;

    @FXML
    private Label lblCategoria;

    @FXML
    private Label lblPreco;

    @FXML
    private Label lblImagemIndisponivel;

    @FXML
    private ImageView imagemProduto;

    @FXML
    private Button btnAdicionar;

    private Produto produto;

    private Consumer<Produto> acaoAdicionar;

    public void configurar(
            Produto produto,
            Consumer<Produto> acaoAdicionar) {

        this.produto = produto;
        this.acaoAdicionar = acaoAdicionar;

        lblNome.setText(produto.getNome());

        lblCategoria.setText(
                produto.getCategoria().getNomeExibicao());

        lblPreco.setText(formatarPreco(produto.getPreco()));

        carregarImagem(produto.getCaminhoImagem());
    }

    private String formatarPreco(BigDecimal preco) {

        NumberFormat formato = NumberFormat.getCurrencyInstance(
                Locale.of("pt", "BR"));

        return formato.format(preco);
    }

    private void carregarImagem(String caminhoImagem) {

        ImagemUtil.carregarImagem(
                caminhoImagem,
                imagemProduto,
                lblImagemIndisponivel);
    }

    @FXML
    private void adicionarAoCarrinho() {

        if (produto != null && acaoAdicionar != null) {
            acaoAdicionar.accept(produto);
        }
    }

    // =============================
    // DETALHES DO PRODUTO
    // =============================

    @FXML
    private void verDetalhes() {

        if (produto == null) {
            return;
        }

        try {

            NavegacaoUtil.abrirProduto(produto);

        } catch (IOException e) {

            LOGGER.log(
                    Level.SEVERE,
                    "Não foi possível abrir os detalhes do produto.",
                    e);

            AlertaUtil.exibirErro(
                    "Erro de navegação",
                    "Não foi possível abrir os detalhes do produto.",
                    e.getMessage());
        }
    }

}
