package com.algaworks.algafood.infrastructure.service.email;


/**
 * 14.10. Implementando a remoção e substituição de arquivos de fotos no serviço de armazenagem<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

public class EmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailException(String message) {
        super(message);
    }

}