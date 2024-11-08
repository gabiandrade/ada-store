package com.gabiandrade.adastore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    @Size(max = 15, message = "Tamanho maximo de 15 caracteres")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Pattern(regexp = "[a-zA-Z]+", message = "Somente letras")
    private String descricao;

    private BigDecimal preco;

    @Pattern(regexp = "[0-9]+", message = "Somente numeros")
    private int quantidade;
}
