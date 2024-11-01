package com.gabiandrade.adastore.mappers;


import com.gabiandrade.adastore.model.Produto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizarProduto(Produto produtoAtualizado, @MappingTarget Produto produtoExistente);
}
