
package com.senai.javafx.brasabrasil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.net.URL;

public class CabecalhoController {

    @FXML
    private void abrirMenu(ActionEvent event) {
        trocarTela(event, "hello-view.fxml");
    }

    @FXML
    private void abrirCadastro(ActionEvent event) {
        trocarTela(event, "cadastro-usuario.fxml");
    }



    @FXML
    private void abrirCardapio(ActionEvent event) {
        System.out.println("1 - BOTÃO CARDÁPIO CLICADO!");

        URL url = getClass().getResource(
                "/com/senai/javafx/brasabrasil/Cardapio.fxml"
        );

        System.out.println("2 - Caminho do FXML: " + url);

        trocarTela(event, "Cardapio.fxml");
    }



    @FXML
    private void abrirHistorico(ActionEvent event) {
        trocarTela(event, "historico.fxml");
    }


    private void trocarTela(ActionEvent event, String arquivo) {
        try {
            URL url = getClass().getResource(
                    "/com/senai/javafx/brasabrasil/" + arquivo
            );

            if (url == null) {
                System.err.println("FXML não encontrado: " + arquivo);
                return;
            }

            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Node origem = (Node) event.getSource();
            Scene cena = origem.getScene();

            cena.setRoot(root);

            System.out.println("Tela aberta: " + arquivo);

        } catch (Exception e) {
            System.err.println("Erro ao abrir: " + arquivo);
            e.printStackTrace();


        }


    }

}

