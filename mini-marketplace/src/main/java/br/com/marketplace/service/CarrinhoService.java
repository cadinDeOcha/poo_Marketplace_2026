package br.com.marketplace.service;

import br.com.marketplace.model.Carrinho;
import br.com.marketplace.model.ItemCarrinho;
import br.com.marketplace.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CarrinhoService {

    private final Carrinho carrinho;

    public CarrinhoService() {
        this.carrinho = new Carrinho();
    }

    // Adiciona um produto ao carrinho
    public void adicionarProduto(Produto produto) {

        if (produto == null) {
            throw new IllegalArgumentException(
                    "O produto não pode ser nulo.");
        }

        Optional<ItemCarrinho> itemExistente = buscarItemPorId(produto.getId());

        if (itemExistente.isPresent()) {

            ItemCarrinho item = itemExistente.get();

            item.alterarQuantidade(
                    item.getQuantidade() + 1);

        } else {

            carrinho.adicionarItem(
                    new ItemCarrinho(produto, 1));
        }
    }

    // Remove um produto pelo ID
    public boolean removerProduto(int idProduto) {

        return carrinho.removerItem(idProduto);
    }

    // Altera a quantidade de um produto
    public void alterarQuantidade(
            int idProduto,
            int novaQuantidade) {

        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero.");
        }

        ItemCarrinho item = buscarItemPorId(idProduto)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Produto não encontrado no carrinho."));

        item.alterarQuantidade(novaQuantidade);
    }

    // Retorna os itens do carrinho
    public List<ItemCarrinho> listarItens() {
        return carrinho.getItens();
    }

    // Retorna o valor total
    public BigDecimal calcularTotal() {
        return carrinho.getTotal();
    }

    // Retorna a quantidade total de unidades
    public int calcularQuantidadeTotal() {
        return carrinho.getQuantidadeTotal();
    }

    // Verifica se o carrinho está vazio
    public boolean estaVazio() {
        return carrinho.estaVazio();
    }

    // Limpa o carrinho
    public void limparCarrinho() {
        carrinho.limpar();
    }

    private Optional<ItemCarrinho> buscarItemPorId(int idProduto) {

        return carrinho.getItens()
                .stream()
                .filter(item -> item.getProduto().getId() == idProduto)
                .findFirst();
    }
}
