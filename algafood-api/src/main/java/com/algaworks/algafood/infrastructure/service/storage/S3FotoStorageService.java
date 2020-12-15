package com.algaworks.algafood.infrastructure.service.storage;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

/**
 * 14.22. Definindo bean do client da Amazon S3 e configurando credenciais<p>
 * 14.23. Implementando a inclusão de objetos no bucket da Amazon S3<p>
 * 14.25. Implementando a recuperação de foto no serviço de storage do S3<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Service
public class S3FotoStorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        String caminhoFoto = getCaminhoArquivo(nomeArquivo);

        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoFoto);

        return FotoRecuperada.builder()
                .url(url.toString()).build();
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());

            ObjectMetadata objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(novaFoto.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    caminhoArquivo,
                    novaFoto.getInputStream(),
                    objectMetaData)
                .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e){
            throw new StorageException("Nao foi possivel enviar o arquivo para Amazon S3.", e);
        }

    }

    private String getCaminhoArquivo(String nomeArquivo) {
        return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
    }

    @Override
    public void remover(String nomeArquivo) {

        try {
            String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getBucket(),
                    caminhoArquivo);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e){
            throw new StorageException("Nao foi possivel excluir o arquivo na Amazon S3.", e);
        }

    }
}
