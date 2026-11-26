package br.com.marketplace.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {

    private final List<ItemCarrinho> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemCarrinho item) {

        if (item == null) {
            throw new IllegalArgumentException(
                    "O item não pode ser nulo.");
        }

        boolean produtoExistente = itens.stream()
                .anyMatch(itemExistente -> itemExistente.getProduto().getId() == item.getProduto().getId());

        if (produtoExistente) {
            throw new IllegalArgumentException(
                    "O produto já está presente no carrinho.");
        }

        itens.add(item);
    }

    public boolean removerItem(int idProduto) {

        return itens.removeIf(
                item -> item.getProduto().getId() == idProduto);
    }

    public List<ItemCarrinho> getItens() {

        return Collections.unmodifiableList(itens);
    }

    public BigDecimal getTotal() {

        return itens.stream()
                .map(ItemCarrinho::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getQuantidadeTotal() {

        return itens.stream()
                .mapToInt(ItemCarrinho::getQuantidade)
                .sum();
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    public void limpar() {
        itens.clear();
    }
}
