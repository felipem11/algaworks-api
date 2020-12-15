package com.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

/**
 * 14.8. Implementando o serviço de armazenagem de fotos no disco local<p>
 * 14.9. Integrando o serviço de catálogo de fotos com o serviço de armazenagem<p>
 * 14.10. Implementando a remoção e substituição de arquivos de fotos no serviço de armazenagem<p>
 * 14.23. Implementando a inclusão de objetos no bucket da Amazon S3<p>
 * 14.25. Implementando a recuperação de foto no serviço de storage do S3<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

public interface FotoStorageService {

    FotoRecuperada recuperar (String nomeArquivo);
    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto){
        this.armazenar(novaFoto);

        if (nomeArquivoAntigo != null){
            this.remover(nomeArquivoAntigo);
        }
    }

    default String gerarNomeArquivo(String nomeOriginal){
        return UUID.randomUUID().toString() + "_" + nomeOriginal;
    }


    @Builder
    @Getter
    class NovaFoto {
        private String nomeArquivo;
        private String contentType;
        private InputStream inputStream;
    }

    @Builder
    @Getter
    class FotoRecuperada {
        private InputStream inputStream;
        private String url;

        public boolean temUrl(){
            return url != null;
        }
        public boolean temInputStream(){
            return inputStream != null;
        }
    }
}
