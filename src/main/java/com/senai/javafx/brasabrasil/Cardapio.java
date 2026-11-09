package com.senai.javafx.brasabrasil;

import javafx.event.ActionEvent;

import java.util.ArrayList;

public class Cardapio {

    private ArrayList<Produto> produtos = new ArrayList<>();

    public Cardapio() {

        // HAMBÚRGUERES
        produtos.add(new Produto(
                "X-Burger",
                18.00,
                "Hambúrgueres"
        ));

        produtos.add(new Produto(
                "X-Salada",
                22.00,
                "Hambúrgueres"
        ));

        produtos.add(new Produto(
                "Brasa Burger",
                28.00,
                "Hambúrgueres"
        ));


        // BEBIDAS
        produtos.add(new Produto(
                "Coca-Cola",
                6.00,
                "Bebidas"
        ));

        produtos.add(new Produto(
                "Suco de Laranja",
                8.00,
                "Bebidas"
        ));

        produtos.add(new Produto(
                "Milkshake",
                12.00,
                "Bebidas"
        ));


        // SOBREMESAS
        produtos.add(new Produto(
                "Brownie",
                10.00,
                "Sobremesas"
        ));

        produtos.add(new Produto(
                "Sorvete",
                8.00,
                "Sobremesas"
        ));

    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<Produto> buscarPorCategoria(String categoria) {

        ArrayList<Produto> resultado = new ArrayList<>();

        for (Produto produto : produtos) {

            if (produto.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(produto);
            }
        }

        return resultado;
    }

    public void mostrarBebidas(ActionEvent actionEvent) {
    }

    public void mostrarHamburgueres(ActionEvent actionEvent) {
    }

    public void mostrarSobremesas(ActionEvent actionEvent) {
    }

    public void mostrarPromocoes(ActionEvent actionEvent) {
    }

    public void limparPedido(ActionEvent actionEvent) {
    }

    public void finalizarPedido(ActionEvent actionEvent) {
    }
}

