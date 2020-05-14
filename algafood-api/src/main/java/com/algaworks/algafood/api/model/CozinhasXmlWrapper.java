package com.algaworks.algafood.api.model;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;

/**
 * 4.16. Customizando a representação em XML com Wrapper e anotações do Jackson
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

//@JacksonXmlRootElement(localName = "cozinhas")
@Data
public class CozinhasXmlWrapper {
	
	@NonNull
	@JsonProperty("cozinha")
//	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Cozinha> cozinhas;

}
