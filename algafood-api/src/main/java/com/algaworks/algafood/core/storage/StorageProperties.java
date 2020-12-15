package com.algaworks.algafood.core.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.castor.core.util.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

/**
 * 14.20. Criando bean de propriedades de configuração dos serviços de storage<p>
 * 14.22. Definindo bean do client da Amazon S3 e configurando credenciais<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {

    private Local local = new Local();
    private S3 s3 = new S3();

    @Getter
    @Setter
    public class Local {
        private Path diretorioFotos;
    }

    @Getter
    @Setter
    public class S3 {
        private String clientId;
        private String clientSecret;
        private StringUtil bucket;
        private Regions region;
        private String diretorioFotos;
    }
}
