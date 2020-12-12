package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


/**
 * 14.5. Mapeando entidade FotoProduto e relacionamento um-para-um<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @see  "https://blog.algaworks.com/lazy-loading-com-mapeamento-onetoone/"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoProduto {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "produto_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;

	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;

}