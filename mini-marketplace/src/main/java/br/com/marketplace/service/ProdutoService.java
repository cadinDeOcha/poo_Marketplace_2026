package br.com.marketplace.service;

import br.com.marketplace.model.Categoria;
import br.com.marketplace.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoService {

    private final List<Produto> produtos;

    public ProdutoService() {

        this.produtos = List.of(

            // ELETRÔNICOS
            new Produto(
                1,
                "Smartphone",
                "Smartphone moderno com tela de alta resolução e excelente desempenho.",
                new BigDecimal("1899.90"),
                Categoria.ELETRONICOS,
                "/br/com/marketplace/images/produtos/smartphone.jpg",
                true
            ),

            new Produto(
                2,
                "Smart TV",
                "Smart TV com resolução 4K e acesso aos principais aplicativos de streaming.",
                new BigDecimal("2499.90"),
                Categoria.ELETRONICOS,
                "/br/com/marketplace/images/produtos/smart-tv.jpg",
                true
            ),

            new Produto(
                3,
                "Tablet",
                "Tablet versátil para estudos, entretenimento e produtividade.",
                new BigDecimal("1299.90"),
                Categoria.ELETRONICOS,
                "/br/com/marketplace/images/produtos/tablet.jpg",
                false
            ),

            // INFORMÁTICA
            new Produto(
                4,
                "Mouse Gamer",
                "Mouse gamer com sensor preciso, iluminação RGB e botões programáveis.",
                new BigDecimal("149.90"),
                Categoria.INFORMATICA,
                "/br/com/marketplace/images/produtos/mouse-gamer.jpg",
                false
            ),

            new Produto(
                5,
                "Teclado Mecânico",
                "Teclado mecânico com iluminação RGB e excelente resposta para jogos e digitação.",
                new BigDecimal("299.90"),
                Categoria.INFORMATICA,
                "/br/com/marketplace/images/produtos/teclado-mecanico.jpg",
                true
            ),

            new Produto(
                6,
                "Monitor",
                "Monitor Full HD com ótima qualidade de imagem para trabalho e entretenimento.",
                new BigDecimal("899.90"),
                Categoria.INFORMATICA,
                "/br/com/marketplace/images/produtos/monitor.jpg",
                false
            ),

            // GAMES
            new Produto(
                7,
                "Controle",
                "Controle sem fio ergonômico para uma experiência confortável nos jogos.",
                new BigDecimal("249.90"),
                Categoria.GAMES,
                "/br/com/marketplace/images/produtos/controle.jpg",
                false
            ),

            new Produto(
                8,
                "Console",
                "Console de videogame com alto desempenho gráfico e grande variedade de jogos.",
                new BigDecimal("3499.90"),
                Categoria.GAMES,
                "/br/com/marketplace/images/produtos/console.jpg",
                true
            ),

            new Produto(
                9,
                "Headset Gamer",
                "Headset gamer com áudio imersivo e microfone integrado.",
                new BigDecimal("199.90"),
                Categoria.GAMES,
                "/br/com/marketplace/images/produtos/headset-gamer.jpg",
                false
            ),

            // ACESSÓRIOS
            new Produto(
                10,
                "Webcam",
                "Webcam Full HD ideal para reuniões, aulas e transmissões ao vivo.",
                new BigDecimal("179.90"),
                Categoria.ACESSORIOS,
                "/br/com/marketplace/images/produtos/webcam.jpg",
                true
            ),

            new Produto(
                11,
                "Mousepad",
                "Mousepad com superfície confortável e base antiderrapante.",
                new BigDecimal("59.90"),
                Categoria.ACESSORIOS,
                "/br/com/marketplace/images/produtos/mousepad.jpg",
                false
            ),

            new Produto(
                12,
                "Hub USB",
                "Hub USB compacto para conectar vários dispositivos ao computador.",
                new BigDecimal("89.90"),
                Categoria.ACESSORIOS,
                "/br/com/marketplace/images/produtos/hub-usb.jpg",
                false
            )
        );
    }

    // Retorna todos os produtos
    public List<Produto> listarProdutos() {
        return produtos;
    }

    // Busca um produto pelo ID
    public Optional<Produto> buscarPorId(int id) {

        return produtos.stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

    // Pesquisa por nome e filtra por categoria
    public List<Produto> buscarProdutos(
            String termo,
            Categoria categoria) {

        String pesquisa = termo == null
                ? ""
                : termo.trim().toLowerCase();

        return produtos.stream()

                .filter(produto ->
                    produto.getNome()
                            .toLowerCase()
                            .contains(pesquisa)
                )

                .filter(produto ->
                    categoria == null
                    || produto.getCategoria() == categoria
                )

                .collect(Collectors.toList());
    }

    // Retorna somente os produtos em destaque
    public List<Produto> listarDestaques() {

        return produtos.stream()
                .filter(Produto::isDestaque)
                .collect(Collectors.toList());
    }
}
