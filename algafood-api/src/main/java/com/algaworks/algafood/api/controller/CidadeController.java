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
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ<p>
 * 8.6. Desafio: refatorando os serviços REST<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */


@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@GetMapping
	private List<Cidade> listar(){
		return cidadeRepository.findAll(); 
	}
	
	@GetMapping("{id}")
	private Cidade buscarCidade(@PathVariable Long id){
		return cadastroCidade.buscarOuFalhar(id); 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Cidade salvar(@RequestBody Cidade cidade) {
		return cadastroCidade.salvar(cidade);
		
	}
	
	@PutMapping("/{id}")
	private Cidade alterar(@PathVariable Long id, @RequestBody Cidade cidade){
		Cidade cidadeDB = cadastroCidade.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(cidade, cidadeDB, "id");
		
		return cadastroCidade.salvar(cidadeDB);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void  excluir(@PathVariable Long id){
			cadastroCidade.excluir(id);
		
	}

}
