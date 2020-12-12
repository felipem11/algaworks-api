package com.algaworks.algafood.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 14.3. Validando o tamanho máximo do arquivo<P>
 * 14.4. Desafio: Validando o content type do arquivo<P>
 * @see  "https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */


@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { FileSizeValidator.class })
public @interface FileSize {
	
	String message() default "tamanho do arquivo invalido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String max();

}
