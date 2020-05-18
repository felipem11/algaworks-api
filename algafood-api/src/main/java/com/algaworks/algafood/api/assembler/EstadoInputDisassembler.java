package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;
/**
 * 11.14. Adicionando e usando o ModelMapper<p>
 * @see  http://modelmapper.org/
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Component
public class EstadoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}
	
	public Estado toDomainObject(EstadoInput EstadoInput) {
		return modelMapper.map(EstadoInput, Estado.class);
	}
	
}
