package br.com.marketplace.util;

import br.com.marketplace.controller.CatalogoController;
import br.com.marketplace.controller.ProdutoController;
import br.com.marketplace.model.Produto;
import br.com.marketplace.service.CarrinhoService;
import br.com.marketplace.service.ProdutoService;
import br.com.marketplace.controller.CarrinhoController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavegacaoUtil {

    private static Stage stage;

    private static Scene scene;

    private static ProdutoService produtoService;

    private static CarrinhoService carrinhoService;

    private static Parent telaCatalogo;

    private static CatalogoController catalogoController;

    private NavegacaoUtil() {
        // Impede a instanciação da classe utilitária.
    }

    // =============================
    // INICIALIZAÇÃO
    // =============================

    public static void inicializar(
            Stage janela,
            ProdutoService servicoProdutos,
            CarrinhoService servicoCarrinho) {

        stage = janela;
        produtoService = servicoProdutos;
        carrinhoService = servicoCarrinho;
    }

    // =============================
    // CATÁLOGO
    // =============================

    public static void abrirCatalogo() throws IOException {

        if (telaCatalogo == null) {

            FXMLLoader loader = new FXMLLoader(
                    NavegacaoUtil.class.getResource(
                        "/br/com/marketplace/view/catalogo.fxml"
                    )
            );

            catalogoController = new CatalogoController(
                    produtoService,
                    carrinhoService
            );

            loader.setController(catalogoController);

            telaCatalogo = loader.load();
        }

        catalogoController.atualizarIndicadorCarrinho();

        trocarTela(telaCatalogo);
    }

    // =============================
    // DETALHES DO PRODUTO
    // =============================

    public static void abrirProduto(
            Produto produto) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                NavegacaoUtil.class.getResource(
                    "/br/com/marketplace/view/produto.fxml"
                )
        );

        ProdutoController controller =
                new ProdutoController(
                        produto,
                        carrinhoService
                );

        loader.setController(controller);

        Parent telaProduto = loader.load();

        trocarTela(telaProduto);
    }

    // =============================
    // TROCA DE TELA
    // =============================

    private static void trocarTela(Parent root) {

        if (scene == null) {

            scene = new Scene(root, 1100, 750);

            scene.getStylesheets().add(
                    NavegacaoUtil.class.getResource(
                        "/br/com/marketplace/css/style.css"
                    ).toExternalForm()
            );

            stage.setScene(scene);

        } else {

            scene.setRoot(root);
        }
    }

    // =============================
    // TELA DO CARRINHO
    // =============================

    public static void abrirCarrinho()
            throws IOException {

        FXMLLoader loader = new FXMLLoader(
                NavegacaoUtil.class.getResource(
                    "/br/com/marketplace/view/carrinho.fxml"
                )
        );

        CarrinhoController controller =
                new CarrinhoController(carrinhoService);

        loader.setController(controller);

        Parent telaCarrinho = loader.load();

        scene.setRoot(telaCarrinho);
    }
}
