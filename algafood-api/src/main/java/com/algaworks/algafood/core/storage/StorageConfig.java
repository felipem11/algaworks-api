package com.algaworks.algafood.core.storage;

import com.algaworks.algafood.core.storage.StorageProperties.TipoStorage;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.infrastructure.service.storage.LocalFotoStorageService;
import com.algaworks.algafood.infrastructure.service.storage.S3FotoStorageService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 14.22. Definindo bean do client da Amazon S3 e configurando credenciais<p>
 * 14.26. Selecionando a implementação do serviço de storage de fotos<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    @ConditionalOnProperty(name = "algafood.storage.tipo", havingValue = "s3")
    public AmazonS3 amazonS3(){
        BasicAWSCredentials credentials = new BasicAWSCredentials(
                storageProperties.getS3().getClientId(),
                storageProperties.getS3().getClientSecret());

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegion())
                .build();
    }

    @Bean
    public FotoStorageService fotoStorageService(){
        if(TipoStorage.S3.equals(storageProperties.getTipoStorage())){
            return new S3FotoStorageService();
        } else {
            return new LocalFotoStorageService();
        }
    }


}
