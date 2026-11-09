
package com.senai.javafx.brasabrasil;

import java.util.ArrayList;

public class BancoDeDados {

    private static BancoDeDados singleton;

    private final ArrayList<Usuario> usuarios = new ArrayList<>();
    private final ArrayList<Pedido> pedidos = new ArrayList<>();

    private BancoDeDados() {
    }

    public static BancoDeDados getInstance() {
        if (singleton == null) {
            singleton = new BancoDeDados();
        }

        return singleton;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public int contarCompras(String gmailUsuario) {
        int quantidade = 0;

        for (Pedido pedido : pedidos) {
            if (pedido.getGmailUsuario().equalsIgnoreCase(gmailUsuario)) {
                quantidade++;
            }
        }

        return quantidade;
    }

    public double calcularTotalGasto(String gmailUsuario) {
        double total = 0;

        for (Pedido pedido : pedidos) {
            if (pedido.getGmailUsuario().equalsIgnoreCase(gmailUsuario)) {
                total += pedido.getTotal();
            }
        }

        return total;
    }
}
