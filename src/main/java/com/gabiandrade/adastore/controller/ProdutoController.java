package com.gabiandrade.adastore.controller;

import com.gabiandrade.adastore.dto.ProdutoDTO;
import com.gabiandrade.adastore.dto.ProdutoListDTO;
import com.gabiandrade.adastore.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criar-produto")
    public ResponseEntity<List<ProdutoDTO>> criarProduto(@RequestBody ProdutoListDTO produtoListDTO) {
        List<ProdutoDTO> novoProduto = produtoService.criarProduto(produtoListDTO);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProdutoDTO>> listarProduto() {
        return ResponseEntity.ok().body(produtoService.listarTodosProdutos());
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @PutMapping("/atualizar-produto/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok().body(produtoService.atualizarProduto(id, produtoDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();  // HTTP 204: Sem conteúdo, deletado com sucesso
        } catch (EntityNotFoundException e) {
            // Caso o produto não seja encontrado, retorna erro 404 (Not Found)
            return ResponseEntity.notFound().build();  // HTTP 404: Produto não encontrado
        }
    }

}
