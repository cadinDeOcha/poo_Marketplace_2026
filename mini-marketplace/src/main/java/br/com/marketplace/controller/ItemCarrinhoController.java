package br.com.marketplace.controller;

import br.com.marketplace.model.ItemCarrinho;
import br.com.marketplace.model.Produto;

import br.com.marketplace.service.CarrinhoService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

public class ItemCarrinhoController {

        @FXML
        private ImageView imgProduto;

        @FXML
        private Label lblImagemIndisponivel;

        @FXML
        private Label lblNome;

        @FXML
        private Label lblCategoria;

        @FXML
        private Label lblPrecoUnitario;

        @FXML
        private Label lblQuantidade;

        @FXML
        private Label lblSubtotal;

        private ItemCarrinho item;

        private final NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(
                        Locale.of("pt", "BR"));

        private ItemCarrinho itemAtual;

        private CarrinhoService carrinhoService;

        private Runnable aoAtualizar;

        @FXML
        private Button btnDiminuir;

        @FXML
        private void aumentarQuantidade() {

                int produtoId = itemAtual.getProduto().getId();

                int novaQuantidade = itemAtual.getQuantidade() + 1;

                carrinhoService.alterarQuantidade(
                                produtoId,
                                novaQuantidade);

                aoAtualizar.run();
        }

        @FXML
        private void diminuirQuantidade() {

                int quantidadeAtual = itemAtual.getQuantidade();

                if (quantidadeAtual <= 1) {
                        return;
                }

                int produtoId = itemAtual.getProduto().getId();

                carrinhoService.alterarQuantidade(
                                produtoId,
                                quantidadeAtual - 1);

                aoAtualizar.run();
        }

        @FXML
        private void removerProduto() {

                int produtoId = itemAtual.getProduto().getId();

                carrinhoService.removerProduto(produtoId);

                aoAtualizar.run();
        }

        // =============================
        // CONFIGURAÇÃO DO ITEM
        // =============================

        public void configurarItem(ItemCarrinho item) {

                this.item = item;

                Produto produto = item.getProduto();

                lblNome.setText(produto.getNome());

                lblCategoria.setText(
                                produto.getCategoria().toString());

                lblPrecoUnitario.setText(
                                formatoMoeda.format(produto.getPreco()));

                lblQuantidade.setText(
                                String.valueOf(item.getQuantidade()));

                lblSubtotal.setText(
                                formatoMoeda.format(item.getSubtotal()));

                carregarImagem(produto.getCaminhoImagem());
        }

        // =============================
        // CARREGAMENTO DA IMAGEM
        // =============================

        private void carregarImagem(String caminhoImagem) {

                imgProduto.setImage(null);
                imgProduto.setVisible(false);
                lblImagemIndisponivel.setVisible(true);

                if (caminhoImagem == null ||
                                caminhoImagem.isBlank()) {
                        return;
                }

                URL recurso = getClass().getResource(caminhoImagem);

                if (recurso != null) {

                        Image imagem = new Image(
                                        recurso.toExternalForm());

                        if (!imagem.isError()) {

                                imgProduto.setImage(imagem);

                                imgProduto.setVisible(true);

                                lblImagemIndisponivel.setVisible(false);
                        }
                }
        }

        public void configurarAcoes(
                        ItemCarrinho item,
                        CarrinhoService carrinhoService,
                        Runnable aoAtualizar) {

                this.itemAtual = item;
                this.carrinhoService = carrinhoService;
                this.aoAtualizar = aoAtualizar;

                btnDiminuir.setDisable(
                                item.getQuantidade() <= 1);
        }

}
