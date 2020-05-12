package com.algaworks.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 4.16. Customizando a representação em XML com Wrapper e anotações do Jackson<p>
 * 4.23. Modelando e implementando a inclusão de recursos com POST<p>
 * 9.7. Agrupando e restringindo constraints que devem ser usadas na validação<p>
 * @see  https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@JsonRootName("cozinha")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(groups = Groups.CozinhaId.class)
	private Long id;
	
//	@JsonProperty("titulo")
	@Column(nullable = false)
	@NotBlank
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>();

}
