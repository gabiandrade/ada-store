package com.gabiandrade.adastore.controller;

import com.gabiandrade.adastore.dto.ProdutoListDTO;
import com.gabiandrade.adastore.model.Produto;
import com.gabiandrade.adastore.service.ProdutoService;
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

    /*localhost:8080/produto/criar-produto*/
    @PostMapping("/criar-produto")
    public ResponseEntity<List<Produto>> criarProduto(@RequestBody ProdutoListDTO produtoListDTO) {
        List<Produto> novoProduto = produtoService.salvarProduto(produtoListDTO);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public List<Produto> listarProduto() {
        return produtoService.listarProdutos();
    }

    //localhost:8080/produto/atualizar-produto/5

    //ResponseEntity

    // 200 - OK
    // 201 - CREATE
    // 400 - BAD REQUEST
    // 404 - NOT FOUND
    // 500 - SERVICO FORA
    @PutMapping("/atualizar-produto/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        return ResponseEntity.ok().body(produtoAtualizado);
        //return produtoAtualizado != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();//return ResponseEntity.ok().body(produtoAtualizado);
    }


}
