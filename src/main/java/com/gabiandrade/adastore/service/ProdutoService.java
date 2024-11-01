package com.gabiandrade.adastore.service;

import com.gabiandrade.adastore.dto.ProdutoListDTO;
import com.gabiandrade.adastore.mappers.ProdutoMapper;
import com.gabiandrade.adastore.model.Produto;
import com.gabiandrade.adastore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    private List<Produto> produtos = new ArrayList<>();

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public List<Produto> salvarProduto(ProdutoListDTO produtoListDTO) {
        return produtoRepository.saveAll(produtoListDTO.getProdutoList());
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto com id" + id + "não encontrado"));
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = buscarProdutoPorId(id);
        produtoMapper.atualizarProduto(produtoAtualizado, produtoExistente);
        return produtoRepository.save(produtoExistente);
    }
}
