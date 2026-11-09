
package com.senai.javafx.brasabrasil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CardapioController {

    @FXML
    private Label tituloCategoria;

    @FXML
    private FlowPane listaProdutos;

    @FXML
    private ListView<String> listaPedido;

    @FXML
    private Label labelTotal;

    @FXML
    private Label labelQuantidade;

    private final Cardapio cardapio = new Cardapio();

    private final List<ProdutoMenu> produtos = new ArrayList<>();
    private final List<ProdutoMenu> pedido = new ArrayList<>();

    private final NumberFormat moeda =
            NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    @FXML
    public void initialize() {
        carregarProdutos();
        mostrarCategoria("Hambúrgueres", "HAMBÚRGUERES");

        atualizarPedido();

        System.out.println("CARDÁPIO: produtos carregados!");
    }

    private void carregarProdutos() {
        produtos.clear();

        for (Produto produto : cardapio.getProdutos()) {

            String descricao;

            switch (produto.getCategoria()) {
                case "Hambúrgueres":
                    descricao = "Um delicioso hambúrguer para seu pedido.";
                    break;

                case "Bebidas":
                    descricao = "Uma bebida para acompanhar seu lanche.";
                    break;

                case "Sobremesas":
                    descricao = "Uma sobremesa deliciosa para finalizar.";
                    break;

                case "Promoções":
                    descricao = "Aproveite esta oferta especial!";
                    break;

                default:
                    descricao = "Confira esta opção do nosso cardápio.";
                    break;
            }

            produtos.add(new ProdutoMenu(
                    produto.getNome(),
                    descricao,
                    produto.getPreco(),
                    produto.getCategoria(),
                    ""
            ));
        }
    }

    @FXML
    private void mostrarHamburgueres() {
        mostrarCategoria("Hambúrgueres", "HAMBÚRGUERES");
    }

    @FXML
    private void mostrarBebidas() {
        mostrarCategoria("Bebidas", "BEBIDAS");
    }

    @FXML
    private void mostrarSobremesas() {
        mostrarCategoria("Sobremesas", "SOBREMESAS");
    }

    @FXML
    private void mostrarPromocoes() {
        mostrarCategoria("Promoções", "OFERTAS ESPECIAIS");
    }

    private void mostrarCategoria(String categoria, String titulo) {
        tituloCategoria.setText(titulo);
        listaProdutos.getChildren().clear();

        for (ProdutoMenu produto : produtos) {
            if (produto.categoria.equals(categoria)) {
                listaProdutos.getChildren().add(criarCard(produto));
            }
        }
    }

    private VBox criarCard(ProdutoMenu produto) {
        VBox card = new VBox(12);
        card.setPrefWidth(285);
        card.setMinWidth(260);
        card.setMaxWidth(310);
        card.setMinHeight(230);

        card.setStyle(
                "-fx-background-color: #1A1A1A;" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-color: #303030;" +
                        "-fx-border-radius: 14;" +
                        "-fx-padding: 18;"
        );

        Label icone = new Label(iconeDaCategoria(produto.categoria));
        icone.setStyle(
                "-fx-font-size: 37px;" +
                        "-fx-background-color: #25221A;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 12;"
        );

        if (produto.destaque != null && !produto.destaque.trim().isEmpty()) {
            Label destaque = new Label(produto.destaque);
            destaque.setStyle(
                    "-fx-text-fill: #FFD84A;" +
                            "-fx-font-size: 10px;" +
                            "-fx-font-weight: bold;"
            );
            card.getChildren().addAll(icone, destaque);
        } else {
            card.getChildren().add(icone);
        }

        Label nome = new Label(produto.nome);
        nome.setWrapText(true);
        nome.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );

        Label descricao = new Label(produto.descricao);
        descricao.setWrapText(true);
        descricao.setMinHeight(42);
        descricao.setStyle(
                "-fx-text-fill: #A5A5A5;" +
                        "-fx-font-size: 12px;"
        );

        Label preco = new Label(moeda.format(produto.preco));
        preco.setStyle(
                "-fx-text-fill: #FFD84A;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        Button adicionar = new Button("＋  ADICIONAR");
        adicionar.setMaxWidth(Double.MAX_VALUE);
        adicionar.setPrefHeight(40);
        adicionar.setStyle(
                "-fx-background-color: #FFD84A;" +
                        "-fx-text-fill: #111111;" +
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 20;" +
                        "-fx-cursor: hand;"
        );

        adicionar.setOnAction(event -> {
            pedido.add(produto);
            atualizarPedido();
        });

        card.getChildren().addAll(nome, descricao, preco, adicionar);

        return card;
    }

    private String iconeDaCategoria(String categoria) {
        switch (categoria) {
            case "Hambúrgueres":
                return "🍔";
            case "Bebidas":
                return "🥤";
            case "Sobremesas":
                return "🍰";
            case "Promoções":
                return "🔥";
            default:
                return "🍽";
        }
    }

    private void atualizarPedido() {
        listaPedido.getItems().clear();

        double total = 0;

        for (ProdutoMenu produto : pedido) {
            listaPedido.getItems().add(
                    produto.nome + "  —  " + moeda.format(produto.preco)
            );
            total += produto.preco;
        }

        labelTotal.setText(moeda.format(total));

        labelQuantidade.setText(
                pedido.size() + (pedido.size() == 1 ? " item" : " itens")
        );
    }

    @FXML
    private void limparPedido() {
        pedido.clear();
        atualizarPedido();
    }


    @FXML
    private void finalizarPedido() {

        // 1. Verifica se o pedido está vazio
        if (pedido.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Pedido vazio");
            alerta.setHeaderText(null);
            alerta.setContentText("Adicione produtos antes de finalizar.");
            alerta.showAndWait();
            return;
        }

        System.out.println("Sessão ativa no cardápio: " + Sessao.estaLogado());

        if (Sessao.getUsuarioLogado() != null) {
            System.out.println(
                    "Usuário atual: " + Sessao.getUsuarioLogado().getGmail()
            );
        }

        System.out.println("========== TESTE DE LOGIN ==========");
        System.out.println("Sessão ativa: " + Sessao.estaLogado());
        System.out.println("Usuário: " + Sessao.getUsuarioLogado());
        System.out.println("====================================");

        // 2. Verifica se o cliente está conectado
        if (!Sessao.estaLogado()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Login necessário");
            alerta.setHeaderText("Você precisa entrar na sua conta.");
            alerta.setContentText(
                    "Faça login ou cadastre-se antes de finalizar o pedido."
            );
            alerta.showAndWait();
            return;
        }

        // 3. Calcula o valor e guarda os nomes dos produtos
        double total = 0;
        List<String> nomesProdutos = new ArrayList<>();

        for (ProdutoMenu produto : pedido) {
            nomesProdutos.add(produto.nome);
            total += produto.preco;
        }

        // 4. Identifica o usuário conectado
        Usuario usuario = Sessao.getUsuarioLogado();

        // 5. Cria e registra o pedido
        Pedido novoPedido = new Pedido(
                usuario.getGmail(),
                nomesProdutos,
                total
        );

        BancoDeDados.getInstance().adicionarPedido(novoPedido);

        // 6. Mostra a confirmação
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Brasa Brasil");
        alerta.setHeaderText("Pedido registrado com sucesso!");
        alerta.setContentText(
                "Cliente: " + usuario.getNome()
                        + "\nQuantidade de itens: " + pedido.size()
                        + "\nValor total: " + moeda.format(total)
        );
        alerta.showAndWait();

        // 7. Limpa o pedido da tela
        pedido.clear();
        atualizarPedido();
    }


    private static class ProdutoMenu {
        private final String nome;
        private final String descricao;
        private final double preco;
        private final String categoria;
        private final String destaque;

        private ProdutoMenu(
                String nome,
                String descricao,
                double preco,
                String categoria,
                String destaque) {
            this.nome = nome;
            this.descricao = descricao;
            this.preco = preco;
            this.categoria = categoria;
            this.destaque = destaque;
        }
    }
}
