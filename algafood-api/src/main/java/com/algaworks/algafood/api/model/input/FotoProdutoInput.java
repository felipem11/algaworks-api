package com.algaworks.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 14.2. Implementando upload de arquivo com multipart/form-data<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Getter
@Setter
public class FotoProdutoInput {

    private MultipartFile arquivo;
    private String descricao;
}
