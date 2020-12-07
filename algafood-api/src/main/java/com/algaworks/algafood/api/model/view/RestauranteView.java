package com.algaworks.algafood.api.model.view;

/**
 * 13.1. Fazendo projeção de recursos com @JsonView do Jackson<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

public interface RestauranteView {

    public interface Resumo {}

    public interface ApenasNome {}
}
