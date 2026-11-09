
package com.senai.javafx.brasabrasil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private final String gmailUsuario;
    private final List<String> produtos;
    private final double total;
    private final LocalDateTime data;

    public Pedido(String gmailUsuario, List<String> produtos, double total) {
        this.gmailUsuario = gmailUsuario;
        this.produtos = new ArrayList<>(produtos);
        this.total = total;
        this.data = LocalDateTime.now();
    }

    public String getGmailUsuario() {
        return gmailUsuario;
    }

    public List<String> getProdutos() {
        return new ArrayList<>(produtos);
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getData() {
        return data;
    }
}
