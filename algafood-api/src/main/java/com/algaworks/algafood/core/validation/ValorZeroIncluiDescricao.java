package com.algaworks.algafood.core.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
/**
 * 9.17. Criando constraints de validação customizadas em nível de classe
 * @see  
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValorZeroIncluiDescricaoValidator.class})
public @interface ValorZeroIncluiDescricao {
	
	String message() default "descrição obrigatoria inválidaB";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String valorField();
	
	String descricaoField();
	
	String descricaoObrigatoria();
	

}
