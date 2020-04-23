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
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@GetMapping
	private List<Cidade> listar(){
		return cidadeRepository.listar(); 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Cidade salvar(@RequestBody Cidade cidade) {
		return cadastroCidade.salvar(cidade);
		
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Cidade> alterar(@PathVariable Long id, @RequestBody Cidade cidade){
		Cidade cidadeDB = cidadeRepository.buscar(id);
		if (cidadeDB == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(cidade, cidadeDB, "id");
		
		cidadeDB = cadastroCidade.salvar(cidadeDB);
		
		return ResponseEntity.ok(cidadeDB);
		
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			cadastroCidade.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		
	}

}
