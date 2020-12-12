package com.algaworks.algafood.core.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * 14.3. Validando o tamanho máximo do arquivo<P>
 * 14.4. Desafio: Validando o content type do arquivo<P>
 * @see  "https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */


public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

	private List<String> allowedContentTypes;

	@Override
	public void initialize(FileContentType constraintAnnotation) {
		this.allowedContentTypes = Arrays.asList(constraintAnnotation.allowed());
	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return value == null
				|| this.allowedContentTypes.contains(value.getContentType());

	}
	
	

}
