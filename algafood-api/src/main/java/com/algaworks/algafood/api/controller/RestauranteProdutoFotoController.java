package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.domain.service.FotoStorageService.FotoRecuperada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 14.2. Implementando upload de arquivo com multipart/form-data<p>
 * 14.3. Validando o tamanho máximo do arquivo<P>
 * 14.4. Desafio: Validando o content type do arquivo<P>
 * 14.6. Implementando serviço de cadastro de foto de produto<p>
 * 14.9. Integrando o serviço de catálogo de fotos com o serviço de armazenagem<p>
 * 14.13. Servindo arquivos de fotos pela API<p>
 * 14.25. Implementando a recuperação de foto no serviço de storage do S3<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProduto;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @Autowired
    private FotoStorageService fotoStorage;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto (@PathVariable Long restauranteId,
                                           @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {
        Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());

        return fotoProdutoModelAssembler.toModel(fotoSalva);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoModel buscar(@PathVariable Long restauranteId,
                                   @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);

        return fotoProdutoModelAssembler.toModel(fotoProduto);
    }

    @GetMapping
    public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId,
                                                          @PathVariable Long produtoId,
                                                          @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        try {
            FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
            verificarCompatibilidadeFoto(mediaTypeFoto, mediaTypesAceitas);

            FotoRecuperada fotoRecuperada = fotoStorage.recuperar(fotoProduto.getNomeArquivo());

//            InputStream inputStream = fotoStorage.recuperar(fotoProduto.getNomeArquivo());

            if (fotoRecuperada.temUrl()){
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));

            }
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long restauranteId,
                        @PathVariable Long produtoId) {
        catalogoFotoProduto.excluir(restauranteId, produtoId);
    }

    private void verificarCompatibilidadeFoto(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

        if (!compativel){
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
        
    }
}