
package com.senai.javafx.brasabrasil;

public class Sessao {

    private static Usuario usuarioLogado;

    public static void iniciar(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static boolean estaLogado() {
        return usuarioLogado != null;
    }

    public static void encerrar() {
        usuarioLogado = null;
    }
}
