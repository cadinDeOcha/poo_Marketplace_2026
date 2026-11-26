package br.com.marketplace.util;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public final class ImagemUtil {

    private ImagemUtil() {
        // Impede a instanciação da classe utilitária.
    }

    public static void carregarImagem(
            String caminhoImagem,
            ImageView imagemProduto,
            Label lblImagemIndisponivel) {

        // Limpa qualquer imagem carregada anteriormente.
        imagemProduto.setImage(null);
        imagemProduto.setVisible(false);
        lblImagemIndisponivel.setVisible(true);

        // Valida o caminho informado.
        if (caminhoImagem == null || caminhoImagem.isBlank()) {
            return;
        }

        // Localiza o recurso no projeto.
        URL recurso = ImagemUtil.class.getResource(caminhoImagem);

        if (recurso == null) {
            return;
        }

        try {

            // Carregamento síncrono da imagem.
            Image imagem = new Image(
                    recurso.toExternalForm(),
                    false);

            // Verifica se houve falha no carregamento.
            if (imagem.isError()) {
                return;
            }

            // Exibe a imagem válida.
            imagemProduto.setImage(imagem);
            imagemProduto.setVisible(true);
            lblImagemIndisponivel.setVisible(false);

        } catch (IllegalArgumentException e) {

            // Mantém a mensagem de imagem indisponível
            // caso o endereço da imagem seja inválido.
        }
    }
}
