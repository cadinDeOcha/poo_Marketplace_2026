package br.com.marketplace;

import br.com.marketplace.service.CarrinhoService;
import br.com.marketplace.service.ProdutoService;
import br.com.marketplace.util.NavegacaoUtil;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Criação dos serviços compartilhados
        ProdutoService produtoService = new ProdutoService();
        CarrinhoService carrinhoService = new CarrinhoService();

        // Inicialização da navegação
        NavegacaoUtil.inicializar(
                stage,
                produtoService,
                carrinhoService);

        // Configurações da janela principal
        stage.setTitle("Mini Marketplace");
        stage.setMinWidth(850);
        stage.setMinHeight(650);

        // Carregamento da primeira tela
        NavegacaoUtil.abrirCatalogo();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
