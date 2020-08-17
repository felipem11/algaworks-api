package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 6.6. Mapeando propriedades com @CreationTimestamp e @UpdateTimestamp<p>
 * 6.12. Alterando a estratégia de fetching para Lazy Loading<p>
 * 6.14. Resolvendo o Problema do N+1 com fetch join na JPQL<p>
 * 9.7. Agrupando e restringindo constraints que devem ser usadas na validação<p>
 * 9.8. Convertendo grupos de constraints para validação em cascata com @ConvertGroup<P>
 * 12.12. Implementando os endpoints de associação de formas de pagamento em restaurantes<p>
 * 12.17. Desafio: implementando endpoints de associação de usuários responsáveis com restaurantes
 * @see  https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotNull
//	@NotEmpty
//	@NotBlank
	@Column(nullable = false)
	private String nome;
	
//	@PositiveOrZero(message = "{taxaFrete.invalida}")
//	@TaxaFrete
//	@Multiplo(numero = 5)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
//	@Valid
//	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
//	@NotNull
	@ManyToOne()
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	private Boolean ativo = Boolean.TRUE;

	private Boolean aberto = Boolean.TRUE;
	
	@Embedded
	private Endereco endereco;
	
	@CreationTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "restaurante_forma_pagamento",
				joinColumns = @JoinColumn(name = "restaurante_id"),
				inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "restaurante_usuario_responsavel",
	joinColumns = @JoinColumn(name = "restaurante_id"),
	inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> usuariosResponsaveis = new HashSet<>();
	
	public void ativar() {
		setAtivo(true);
	}
	public void inativar() {
		setAtivo(false);
	}
	public void abertura() {
		setAberto(true);
	}
	public void fechamento() {
		setAberto(false);
	}
	
	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().remove(formaPagamento);
	}

	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().add(formaPagamento);
	}

	public boolean removerUsuarioResponsavel(Usuario usuario) {
		return getUsuariosResponsaveis().remove(usuario);
	}

	public boolean adicionarUsuarioResponsavel(Usuario usuario) {
		return getUsuariosResponsaveis().add(usuario);
	}

	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return getFormasPagamento().contains(formaPagamento);
	}

	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return !aceitaFormaPagamento(formaPagamento);
	}
	
}
