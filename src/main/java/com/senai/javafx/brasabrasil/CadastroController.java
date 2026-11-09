package com.senai.javafx.brasabrasil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import java.io.IOException;

public class CadastroController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoGmail;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private PasswordField campoConfirmarSenha;

    @FXML
    private Label mensagem;


    @FXML
    public void cadastrar() {

        String nome = campoNome.getText();
        String gmail = campoGmail.getText();
        String senha = campoSenha.getText();
        String confirmarSenha = campoConfirmarSenha.getText();


        // Verifica se os campos estão vazios
        if (nome.isEmpty() ||
                gmail.isEmpty() ||
                senha.isEmpty() ||
                confirmarSenha.isEmpty()) {

            mensagem.setText("Preencha todos os campos.");
            return;
        }


        // Confirma se as senhas são iguais
        if (!senha.equals(confirmarSenha)) {

            mensagem.setText("As senhas não são iguais.");
            return;
        }


        // Cria o usuário
        Usuario usuario = new Usuario(
                nome,
                gmail,
                senha
        );


        // Pega o Banco de Dados Singleton
        BancoDeDados banco = BancoDeDados.getInstance();


        // Adiciona o usuário
        banco.adicionarUsuario(usuario);


        mensagem.setText("Cadastro realizado com sucesso!");


        // Limpa os campos
        campoNome.clear();
        campoGmail.clear();
        campoSenha.clear();


        }

    public void abrirEntrar(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Entrar.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) campoGmail
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void entrar() {

        String gmail = campoGmail.getText().trim();
        String senha = campoSenha.getText();

        if (gmail.isEmpty() || senha.isEmpty()) {
            mensagem.setText("Preencha o Gmail e a senha.");
            return;
        }

        for (Usuario usuario :
                BancoDeDados.getInstance().getUsuarios()) {

            if (usuario.getGmail().equalsIgnoreCase(gmail)
                    && usuario.getSenha().equals(senha)) {

                mensagem.setStyle(
                        "-fx-text-fill: #FFD02F; -fx-font-size: 14px;"
                );

                mensagem.setText(
                        "Bem-vindo ao Brasa Brasil, "
                                + usuario.getNome() + "!"
                );

                return;
            }
        }

        mensagem.setStyle(
                "-fx-text-fill: #FF5555; -fx-font-size: 14px;"
        );

        mensagem.setText("Gmail ou senha incorretos.");
    }

}

