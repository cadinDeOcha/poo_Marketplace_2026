package br.com.marketplace.model;

import java.math.BigDecimal;

public class Produto {

    private final int id;
    private final String nome;
    private final String descricao;
    private final BigDecimal preco;
    private final Categoria categoria;
    private final String caminhoImagem;
    private final boolean destaque;

    public Produto(
            int id,
            String nome,
            String descricao,
            BigDecimal preco,
            Categoria categoria,
            String caminhoImagem,
            boolean destaque) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "O ID deve ser positivo."
            );
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do produto é obrigatório."
            );
        }

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição do produto é obrigatória."
            );
        }

        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O preço não pode ser negativo ou nulo."
            );
        }

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "A categoria é obrigatória."
            );
        }

        if (caminhoImagem == null || caminhoImagem.isBlank()) {
            throw new IllegalArgumentException(
                    "O caminho da imagem é obrigatório."
            );
        }

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.caminhoImagem = caminhoImagem;
        this.destaque = destaque;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public boolean isDestaque() {
        return destaque;
    }
}
