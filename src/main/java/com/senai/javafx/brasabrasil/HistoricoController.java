
package com.senai.javafx.brasabrasil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistoricoController {

    @FXML
    private TableView<Usuario> tabelaUsuarios;

    @FXML
    private TableColumn<Usuario, String> colunaNome;

    @FXML
    private TableColumn<Usuario, String> colunaGmail;

    @FXML
    private Label totalClientes;

    @FXML
    private TextField campoPesquisa;

    @FXML
    private TableColumn<Usuario, Number> colunaCompras;

    @FXML
    private TableColumn<Usuario, String> colunaTotalGasto;

    private ObservableList<Usuario> listaUsuarios;
    private FilteredList<Usuario> usuariosFiltrados;

    @FXML
    public void initialize() {

        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        colunaGmail.setCellValueFactory(
                new PropertyValueFactory<>("gmail")
        );

        BancoDeDados banco = BancoDeDados.getInstance();

        listaUsuarios = FXCollections.observableArrayList(
                banco.getUsuarios()
        );

        usuariosFiltrados = new FilteredList<>(
                listaUsuarios,
                usuario -> true
        );

        tabelaUsuarios.setItems(usuariosFiltrados);

        campoPesquisa.textProperty().addListener(
                (observable, antigo, novo) -> {
                    String pesquisa = novo == null
                            ? ""
                            : novo.trim().toLowerCase();

                    usuariosFiltrados.setPredicate(usuario -> {
                        if (pesquisa.isEmpty()) {
                            return true;
                        }

                        String nome = usuario.getNome() == null
                                ? ""
                                : usuario.getNome().toLowerCase();

                        String gmail = usuario.getGmail() == null
                                ? ""
                                : usuario.getGmail().toLowerCase();

                        return nome.contains(pesquisa)
                                || gmail.contains(pesquisa);
                    });
                }
        );

        atualizarTabela();

        colunaCompras.setCellValueFactory(dados ->
                new javafx.beans.property.ReadOnlyIntegerWrapper(
                        BancoDeDados.getInstance().contarCompras(
                                dados.getValue().getGmail()
                        )
                )
        );

        java.text.NumberFormat moeda =
                java.text.NumberFormat.getCurrencyInstance(
                        new java.util.Locale("pt", "BR")
                );

        colunaTotalGasto.setCellValueFactory(dados ->
                new javafx.beans.property.ReadOnlyStringWrapper(
                        moeda.format(
                                BancoDeDados.getInstance().calcularTotalGasto(
                                        dados.getValue().getGmail()
                                )
                        )
                )
        );
    }

    @FXML
    private void atualizarTabela() {

        BancoDeDados banco = BancoDeDados.getInstance();

        listaUsuarios.setAll(banco.getUsuarios());

        tabelaUsuarios.refresh();

        totalClientes.setText(
                String.valueOf(listaUsuarios.size())
        );
    }
}
