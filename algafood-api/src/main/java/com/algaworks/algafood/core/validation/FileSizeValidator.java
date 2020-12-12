package com.algaworks.algafood.core.validation;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 14.3. Validando o tamanho máximo do arquivo<P>
 * 14.4. Desafio: Validando o content type do arquivo<P>
 * @see  "https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */


public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private DataSize maxSize;
	@Override
	public void initialize(FileSize constraintAnnotation) {
		this.maxSize = DataSize.parse(constraintAnnotation.max());
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

		return value == null || value.getSize() <= this.maxSize.toBytes();

	}
	
	

}
