package com.algaworks.algafood.api.controller;

import java.util.List;

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

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	private List<Estado> listar(){
		return estadoRepository.listar();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstadoService.salvar(estado);
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Estado> alterar(@PathVariable Long id, 
			@RequestBody Estado estado){
		
		Estado estadoDB = estadoRepository.buscar(id);
		if (estadoDB == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(estado, estadoDB, "id");
		
		estado = cadastroEstadoService.salvar(estadoDB);
		return ResponseEntity.ok(estado);
		
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			cadastroEstadoService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch(EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}
