package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 14.6. Implementando serviço de cadastro de foto de produto<p>
 * @see  "http://modelmapper.org/"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Getter
@Setter
public class FotoProdutoModel {

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

}
