package com.gabiandrade.adastore.service;

import com.gabiandrade.adastore.dto.ProdutoDTO;
import com.gabiandrade.adastore.dto.ProdutoListDTO;
import com.gabiandrade.adastore.mappers.ProdutoMapper;
import com.gabiandrade.adastore.model.ProdutoEntity;
import com.gabiandrade.adastore.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;


    public List<ProdutoDTO> criarProduto(ProdutoListDTO produtoListDTO) {
        List<ProdutoEntity> produtoEntities = produtoListDTO.getProdutoList().stream()
                .map(produtoMapper::toEntity)
                .collect(Collectors.toList());

        List<ProdutoEntity> produtosSalvos = produtoRepository.saveAll(produtoEntities);

        return produtosSalvos.stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarProdutoPorId(Long id) {
        ProdutoEntity produtoEntity = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produto com id" + id + "não encontrado"));
        return produtoMapper.toDTO(produtoEntity);
    }

    public List<ProdutoDTO> listarTodosProdutos() {
        List<ProdutoEntity> produtoEntities = produtoRepository.findAll();
        return produtoEntities.stream().map(produtoMapper::toDTO).collect(Collectors.toList());
    }

    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(id);
        if (produtoEntity.isPresent()) {
            ProdutoEntity produtoExistente = produtoEntity.get();
            produtoMapper.atualizarProduto(produtoDTO, produtoExistente);
            ProdutoEntity produtoSalvo = produtoRepository.save(produtoExistente);

            return produtoMapper.toDTO(produtoSalvo);
        } else {
            throw new EntityNotFoundException("Produto não encontrado com id " + id);
        }
    }


    public void deletarProduto(Long id) {
        ProdutoEntity produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id " + id));

        produtoRepository.delete(produtoExistente);
    }


}
