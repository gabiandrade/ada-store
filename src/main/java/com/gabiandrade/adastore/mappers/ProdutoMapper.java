package com.gabiandrade.adastore.mappers;


import com.gabiandrade.adastore.dto.ProdutoDTO;
import com.gabiandrade.adastore.model.ProdutoEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoEntity toEntity(ProdutoDTO produtoDTO);

    ProdutoDTO toDTO(ProdutoEntity produtoEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizarProduto(ProdutoDTO produtoAtualizado, @MappingTarget ProdutoEntity produtoExistente);
}
