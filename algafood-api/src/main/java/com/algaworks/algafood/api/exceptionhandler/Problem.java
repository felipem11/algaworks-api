package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;
/**
 * 8.18. Padronizando o formato de problemas no corpo de respostas com a RFC 7807<br>
 * 18.15. Referenciando modelo de representação de problema com códigos de status de erro<br>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter 
@Builder
public class Problem {

	@ApiModelProperty(example = "400", position = 1)
	private Integer status;

	@ApiModelProperty(example = "http://felipefood.com/mensagem-incompreensivel", position = 5)
	private String type;

	@ApiModelProperty(example = "Mensagem incompreensível", position = 10)
	private String title;

	@ApiModelProperty(example = "A propriedade 'nome' não existe. Corrija ou remova essa propriedade e tente novamente.", position = 15)
	private String detail;

	@ApiModelProperty(position = 20)
	private String userMessage;

	@ApiModelProperty(example = "2020-12-30T18:40:58.525641Z", position = 25)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private OffsetDateTime timestamp;

	@ApiModelProperty("Objetos ou campo que geraram o erro")
	private List<Object> objects;

	@ApiModel("Objeto Problema")
	@Getter
	@Builder
	public static class Object {

		@ApiModelProperty("preco")
		private String name;

		@ApiModelProperty("O preco e obrigatorio")
		private String userMessage;
	}

}
