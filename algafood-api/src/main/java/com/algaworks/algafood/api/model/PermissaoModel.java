package com.algaworks.algafood.api.model;
import lombok.Getter;
import lombok.Setter;
/**
 * 12.15. Desafio: implementando os endpoints de associação de grupos com permissões
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */


@Setter
@Getter
public class PermissaoModel {
	
	private Long id;
	private String nome;
	private String descricao;

}
