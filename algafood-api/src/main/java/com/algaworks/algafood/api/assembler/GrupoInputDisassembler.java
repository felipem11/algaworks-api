package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
/**
 * 12.8. Desafio: implementando os endpoints de grupos<p>
 * @see  http://modelmapper.org/
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Component
public class GrupoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
		modelMapper.map(grupoInput, grupo);
	}
	
	public Grupo toDomainObject(GrupoInput GrupoInput) {
		return modelMapper.map(GrupoInput, Grupo.class);
	}
	
}
