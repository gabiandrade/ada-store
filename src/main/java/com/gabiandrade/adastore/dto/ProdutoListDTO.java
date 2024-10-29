package com.gabiandrade.adastore.dto;


import com.gabiandrade.adastore.model.Produto;
import lombok.Getter;

import java.util.List;

@Getter
public class ProdutoListDTO {
    private List<Produto> produtoList;
}
