package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
/**
 * 8.18. Padronizando o formato de problemas no corpo de respostas com a RFC 7807<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@JsonInclude(Include.NON_NULL)
@Getter 
@Builder
public class Problem {
	
	private Integer status;
	private String type;
	private String title;
	private String detail;

}
