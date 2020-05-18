package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 11.14. Adicionando e usando o ModelMapper<p>
 * 11.16. Customizando o mapeamento de propriedades com ModelMapper<p>
 * @see  http://modelmapper.org/
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		
		var modelMapper = new ModelMapper();
		
//		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
		
		return modelMapper;
	}

}
