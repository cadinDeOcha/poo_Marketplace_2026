package br.com.marketplace.controller;

import br.com.marketplace.model.Produto;

import br.com.marketplace.util.NavegacaoUtil;

import javafx.scene.control.Alert;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;

public class ProdutoCardController {

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
                produto.getCategoria().getNomeExibicao()
        );

        lblPreco.setText(formatarPreco(produto.getPreco()));

        carregarImagem(produto.getCaminhoImagem());
    }

    private String formatarPreco(BigDecimal preco) {

        NumberFormat formato = NumberFormat.getCurrencyInstance(
                new Locale("pt", "BR")
        );

        return formato.format(preco);
    }

    private void carregarImagem(String caminhoImagem) {

        var recurso = getClass().getResource(caminhoImagem);

        if (recurso != null) {

            imagemProduto.setImage(
                    new Image(recurso.toExternalForm())
            );

            imagemProduto.setVisible(true);
            lblImagemIndisponivel.setVisible(false);

        } else {

            imagemProduto.setVisible(false);
            lblImagemIndisponivel.setVisible(true);
        }
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

            Alert alerta = new Alert(
                    Alert.AlertType.ERROR
            );

            alerta.setTitle("Erro de navegação");

            alerta.setHeaderText(
                    "Não foi possível abrir os detalhes do produto."
            );

            alerta.setContentText(e.getMessage());

            alerta.showAndWait();

            e.printStackTrace();
        }
    }

}
