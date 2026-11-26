package br.com.marketplace.util;

import javafx.scene.control.Alert;

public final class AlertaUtil {

    private AlertaUtil() {
        // Impede a instanciação da classe utilitária.
    }

    public static void exibirErro(
            String titulo,
            String cabecalho,
            String detalhes) {

        Alert alerta = new Alert(
                Alert.AlertType.ERROR);

        alerta.setTitle(titulo);

        alerta.setHeaderText(cabecalho);

        alerta.setContentText(detalhes);

        alerta.showAndWait();
    }
}
