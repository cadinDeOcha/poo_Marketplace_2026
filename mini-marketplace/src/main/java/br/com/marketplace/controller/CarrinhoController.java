package br.com.marketplace.controller;

import br.com.marketplace.model.ItemCarrinho;
import br.com.marketplace.service.CarrinhoService;
import br.com.marketplace.util.NavegacaoUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    private final NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(
            Locale.of("pt", "BR"));

    @FXML
    private Label lblQuantidadeCarrinho;

    @FXML
    private Label lblQuantidadeProdutos;

    @FXML
    private Label lblTotal;

    @FXML
    private VBox painelItens;

    @FXML
    private VBox painelCarrinhoVazio;

    // =============================
    // CONSTRUTOR
    // =============================

    public CarrinhoController(
            CarrinhoService carrinhoService) {

        this.carrinhoService = carrinhoService;
    }

    // =============================
    // INICIALIZAÇÃO
    // =============================

    @FXML
    private void initialize() {

        atualizarCarrinho();
    }

    // =============================
    // ATUALIZAÇÃO DO CARRINHO
    // =============================

    public void atualizarCarrinho() {

        carregarItens();

        atualizarResumo();

        atualizarEstadoCarrinho();
    }

    // =============================
    // CARREGAMENTO DOS ITENS
    // =============================

    private void carregarItens() {

        painelItens.getChildren().clear();

        List<ItemCarrinho> itens = carrinhoService.listarItens();

        for (ItemCarrinho item : itens) {

            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/br/com/marketplace/view/item-carrinho.fxml"));

                Parent componente = loader.load();

                ItemCarrinhoController controller = loader.getController();

                controller.configurarItem(item);

                controller.configurarAcoes(
                        item,
                        carrinhoService,
                        this::atualizarCarrinho);

                painelItens.getChildren().add(componente);

            } catch (IOException e) {

                exibirErro(
                        "Não foi possível carregar um item do carrinho.",
                        e);
            }
        }
    }

    // =============================
    // RESUMO DO CARRINHO
    // =============================

    private void atualizarResumo() {

        int quantidadeTotal = carrinhoService.calcularQuantidadeTotal();

        lblQuantidadeCarrinho.setText(
                "Carrinho (" + quantidadeTotal + ")");

        lblQuantidadeProdutos.setText(
                quantidadeTotal +
                        (quantidadeTotal == 1
                                ? " unidade"
                                : " unidades"));

        lblTotal.setText(
                formatoMoeda.format(
                        carrinhoService.calcularTotal()));
    }

    // =============================
    // ESTADO DO CARRINHO
    // =============================

    private void atualizarEstadoCarrinho() {

        boolean vazio = carrinhoService.estaVazio();

        painelCarrinhoVazio.setVisible(vazio);
        painelCarrinhoVazio.setManaged(vazio);

        painelItens.setVisible(!vazio);
        painelItens.setManaged(!vazio);
    }

    // =============================
    // NAVEGAÇÃO
    // =============================

    @FXML
    private void voltarCatalogo() {

        try {

            NavegacaoUtil.abrirCatalogo();

        } catch (IOException e) {

            exibirErro(
                    "Não foi possível retornar ao catálogo.",
                    e);
        }
    }

    // =============================
    // TRATAMENTO DE ERROS
    // =============================

    private void exibirErro(
            String mensagem,
            Exception e) {

        Alert alerta = new Alert(
                Alert.AlertType.ERROR);

        alerta.setTitle("Erro");

        alerta.setHeaderText(mensagem);

        alerta.setContentText(e.getMessage());

        alerta.showAndWait();

        e.printStackTrace();
    }
}
