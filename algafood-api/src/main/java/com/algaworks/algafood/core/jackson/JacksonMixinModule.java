package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.api.model.mixin.RestauranteMixin;
import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
/**
 * 11.3. Criando classes de mixin para usar as anotações do Jackson
 * @see  
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
	}

}
