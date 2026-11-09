package com.senai.javafx.brasabrasil;

public class Usuario {

    private String nome;
    private String gmail;
    private String senha;

    public Usuario(String nome, String gmail, String senha) {
        this.nome = nome;
        this.gmail = gmail;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getGmail() {
        return gmail;
    }

    public String getSenha() {
        return senha;
    }
}