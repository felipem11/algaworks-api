package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
/**
 * 12.9. Desafio: implementando os endpoints de usuarios<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Getter
@Setter
public class SenhaInput {
	
	
    @NotBlank
    private String senhaAtual;
    
    @NotBlank
    private String novaSenha;
	
	
}
