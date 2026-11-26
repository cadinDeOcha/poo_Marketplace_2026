package br.com.marketplace.controller;

import br.com.marketplace.model.Produto;
import br.com.marketplace.service.CarrinhoService;
import br.com.marketplace.util.NavegacaoUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

public class ProdutoController {

    private final Produto produto;

    private final CarrinhoService carrinhoService;

    private final NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(
            Locale.forLanguageTag("pt-BR"));

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

        carregarImagem();
    }

    // =============================
    // IMAGEM DO PRODUTO
    // =============================

    private void carregarImagem() {

        imagemProduto.setVisible(false);
        lblImagemIndisponivel.setVisible(true);

        URL caminhoImagem = getClass().getResource(
                produto.getCaminhoImagem());

        if (caminhoImagem != null) {

            Image imagem = new Image(
                    caminhoImagem.toExternalForm());

            if (!imagem.isError()) {

                imagemProduto.setImage(imagem);

                imagemProduto.setVisible(true);

                lblImagemIndisponivel.setVisible(false);
            }
        }
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

            Alert alerta = new Alert(
                    Alert.AlertType.ERROR);

            alerta.setTitle("Erro de navegação");

            alerta.setHeaderText(
                    "Não foi possível abrir o carrinho.");

            alerta.setContentText(e.getMessage());

            alerta.showAndWait();

            e.printStackTrace();
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

            Alert alerta = new Alert(
                    Alert.AlertType.ERROR);

            alerta.setTitle("Erro de navegação");

            alerta.setHeaderText(
                    "Não foi possível abrir o catálogo.");

            alerta.setContentText(e.getMessage());

            alerta.showAndWait();
        }
    }
}
