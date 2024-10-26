package com.gabiandrade.adastore.service;

import com.gabiandrade.adastore.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto salvarProduto(Produto produto) {
        produtos.add(produto);
        return produto;
    }

    public Produto atualizarProduto(Long id, Produto produto) {
        Optional<Produto> produtoOptional =
                produtos.stream()
                        .filter(p -> Objects.equals(p.getId(), id))
                        .findFirst();

        if (produtoOptional.isPresent()) {
            Produto novoProduto = produtoOptional.get();
            novoProduto.setNome(produto.getNome());
            novoProduto.setDescricao(produto.getDescricao());
            novoProduto.setPreco(produto.getPreco());
            novoProduto.setQuantidade(produto.getQuantidade());
            return novoProduto;
        }
        return null;
    }
}
