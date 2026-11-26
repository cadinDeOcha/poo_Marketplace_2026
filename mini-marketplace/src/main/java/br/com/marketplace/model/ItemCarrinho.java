package br.com.marketplace.model;

import java.math.BigDecimal;

public class ItemCarrinho {

    private final Produto produto;
    private int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {

        if (produto == null) {
            throw new IllegalArgumentException(
                    "O produto não pode ser nulo."
            );
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );
        }

        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void alterarQuantidade(int quantidade) {

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );
        }

        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal() {

        return produto.getPreco().multiply(
                BigDecimal.valueOf(quantidade)
        );
    }
}
