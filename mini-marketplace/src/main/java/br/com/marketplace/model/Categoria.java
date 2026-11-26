package br.com.marketplace.model;

public enum Categoria {

    ELETRONICOS("Eletrônicos"),
    INFORMATICA("Informática"),
    GAMES("Games"),
    ACESSORIOS("Acessórios");

    private final String nomeExibicao;

    Categoria(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    @Override
    public String toString() {
        return nomeExibicao;
    }
}
