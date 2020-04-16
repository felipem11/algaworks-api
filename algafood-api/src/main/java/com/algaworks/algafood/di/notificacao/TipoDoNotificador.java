package com.algaworks.algafood.di.notificacao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */
@Retention(RetentionPolicy.RUNTIME)	//2.19. Desambiguação de beans com anotação customizada
@Qualifier
public @interface TipoDoNotificador {
	
	NivelUrgencia value();

}
