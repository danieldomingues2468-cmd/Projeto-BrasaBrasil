
package com.senai.javafx.brasabrasil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EntrarController {

    @FXML
    private TextField campoGmail;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private void entrar() {

        String gmail = campoGmail.getText().trim();
        String senha = campoSenha.getText();

        // Verifica se os campos estão preenchidos
        if (gmail.isEmpty() || senha.isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Preencha o Gmail e a senha."
            );
            return;
        }

        // Procura o usuário cadastrado
        for (Usuario usuario :
                BancoDeDados.getInstance().getUsuarios()) {

            if (usuario.getGmail().equalsIgnoreCase(gmail)
                    && usuario.getSenha().equals(senha)) {

                // Guarda o usuário que entrou na conta
                Sessao.iniciar(usuario);

                System.out.println("LOGIN REALIZADO");
                System.out.println("Sessão: " + Sessao.estaLogado());
                System.out.println("Usuário: " +
                        Sessao.getUsuarioLogado().getGmail());

                mostrarAlerta(
                        Alert.AlertType.INFORMATION,
                        "Bem-vindo, " + usuario.getNome() + "!"
                );

                return;
            }
        }

        // Se não encontrar o usuário, informa o erro
        mostrarAlerta(
                Alert.AlertType.ERROR,
                "Gmail ou senha incorretos."
        );
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String mensagem) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle("Brasa Brasil");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
