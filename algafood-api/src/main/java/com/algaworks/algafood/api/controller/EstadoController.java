package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ
 * 8.6. Desafio: refatorando os serviços REST<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	private List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("{id}")
	private Estado buscar(@PathVariable Long id){
		return cadastroEstadoService.buscarOuFalhar(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstadoService.salvar(estado);
	}
	
	@PutMapping("/{id}")
	private Estado alterar(@PathVariable Long id, 
			@RequestBody Estado estado){
		
		Estado estadoDB = cadastroEstadoService.buscarOuFalhar(id);

		BeanUtils.copyProperties(estado, estadoDB, "id");
		
		return cadastroEstadoService.salvar(estadoDB);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void excluir(@PathVariable Long id){
			cadastroEstadoService.excluir(id);
		
	}

}
