package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
/**
 * 8.16. Customizando o corpo da resposta padrão de ResponseEntityExceptionHandler<p>
 * 8.18. Padronizando o formato de problemas no corpo de respostas com a RFC 7807<p>	
 * 8.21. Tratando a exception InvalidFormatException na desserialização<p>
 * 9.5. Conhecendo e adicionando mais constraints de validação no modelo<p>
 * 9.11. Customizando e resolvendo mensagens de validação globais em Resource Bundle<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
	        + "Tente novamente e se o problema persistir, entre em contato "
	        + "com o administrador do sistema.";

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;

		String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente.",
				ex.getRequestURL());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
		protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}
		
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		}
	
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

		String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
		
//		ex.getPath().forEach(p -> System.out.println(p.getFrom().getClass()));
//		
//		ex.getPath().forEach(ref -> System.out.println(ref.getFieldName()));
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		String detail = String.format("A propriedade '%s' é desconhecida para a URI '%s'.",
				path, uri);
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		
		ex.getPath().forEach(ref -> System.out.println(ref.getFieldName()));
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

		String detail = String.format("O parametro de URL '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe o valor compatível com o tipo %s",
					ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
//		Problem problem = Problem.builder()
//				.status(status.value())
//				.type("http://felipefood.com/entidade-nao-encontrada")
//				.title("Entidade não encontrada")
//				.detail(ex.getMessage())
//				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), 
				HttpStatus.CONFLICT, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	        HttpHeaders headers, HttpStatus status, WebRequest request) {
	    return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
	}
	
	private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		        
		    ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		    
		    List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
		            .map(objectError -> {
		                String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
		                
		                String name = objectError.getObjectName();
		                
		                if (objectError instanceof FieldError) {
		                    name = ((FieldError) objectError).getField();
		                }
		                
		                return Problem.Object.builder()
		                    .name(name)
		                    .userMessage(message)
		                    .build();
		            })
		            .collect(Collectors.toList());
		    
		    Problem problem = createProblemBuilder(status, problemType, detail)
		        .userMessage(detail)
		        .objects(problemObjects)
		        .build();
		    
		    return handleExceptionInternal(ex, problem, headers, status, request);
		}
	
	@ExceptionHandler({ ValidacaoException.class })
	public ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
	    return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), 
	            HttpStatus.BAD_REQUEST, request);
	}  
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problem.builder()
					.title(status.getReasonPhrase())
					.timestamp(OffsetDateTime.now())
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
					.status(status.value()).build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.title((String) body)
					.timestamp(OffsetDateTime.now())
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
					.status(status.value()).build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
	    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;		
	    ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
	    String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

	    // Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
	    // fazendo logging) para mostrar a stacktrace no console
	    // Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
	    // para você durante, especialmente na fase de desenvolvimento
	    ex.printStackTrace();
	    
	    Problem problem = createProblemBuilder(status, problemType, detail).build();

	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}        
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, 
			ProblemType problemType, String detail){
		
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.timestamp(OffsetDateTime.now())
				.detail(detail);
	}
	

}
