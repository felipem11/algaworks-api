package com.algaworks.algafood.di.service;

/**
 */


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

/**
 * 2.22. Publicando e consumindo eventos customizados
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Component
public class AtivacaoClienteService {
	
//	@TipoDoNotificador(NivelUrgencia.NORMAL) // 
//	@Qualifier("urgente")	// 
//	@Autowired //(required = false) 
//	private Notificador notificadores;
//	private List<Notificador> notificadores;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
		
		
//		notificadores.notificar(cliente, "Seu cadastro no sistema está ativo!");
		
//		if (!notificadores.isEmpty()) {
//			notificadores.forEach(n -> n.notificar(cliente, "Seu cadastro no sistema está ativo!"));
//		} else {
//			System.out.println("Nao existe notificador, mas cliente foi ativado");
//		}
	}

	@PostConstruct
	public void init() {
		System.out.println("INIT");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("DESTROY");
	}
	
//	Anotação utilizada para marcar o ponto de injeção na sua classe. Você pode colocar ela sobre atributos ou sobre o seu construtor com argumentos.
//	Indica ao Spring qual construtor vai utilizar em caso de mais de um 
//	@Autowired 
//	public AtivacaoClienteService(Notificador notificador) {
//		this.notificador = notificador;
//		System.out.println("AtivacaoClienteService: " + notificador.getClass().getSimpleName());
//	}
	
//	public AtivacaoClienteService(String exemplo) {
//		System.out.println("Apenas para demonstrar o Autowired");
//	}

	
//	@Autowired
//	public void setNotificador(Notificador notificador) {
//		this.notificador = notificador;
//	}
	
	
	
}
