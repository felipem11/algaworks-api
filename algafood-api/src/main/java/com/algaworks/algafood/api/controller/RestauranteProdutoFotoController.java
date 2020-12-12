package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.UUID;

/**
 * 14.2. Implementando upload de arquivo com multipart/form-data<p>
 * 14.3. Validando o tamanho máximo do arquivo<P>
 * 14.4. Desafio: Validando o content type do arquivo<P>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto (@PathVariable Long restauranteId,
                               @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput){

        String nomeArquivo = UUID.randomUUID().toString() + "_"
                + fotoProdutoInput.getArquivo().getOriginalFilename();
        var arquivoFoto = Path.of("/home/felipe/Desktop", nomeArquivo);

        System.out.println(fotoProdutoInput.getDescricao());
        System.out.println(arquivoFoto);
        System.out.println(fotoProdutoInput.getArquivo().getContentType());

        try {
            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}