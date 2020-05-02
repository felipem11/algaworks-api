package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

/**
 * 4.10. Modelando e requisitando um Collection Resource com GET<p>
 * 4.13. Implementando content negotiation para retornar JSON ou XML<p>
 * 4.14. Consultando Singleton Resource com GET e @PathVariable<p>
 * 4.16. Customizando a representação em XML com Wrapper e anotações do Jackson<p>
 * 4.23. Modelando e implementando a inclusão de recursos com POST<p>
 * 4.25. Modelando e implementando a atualização de recursos com PUT<p>
 * 4.26. Modelando e implementando a exclusão de recursos com DELETE<p>
 * 4.28. Refatorando a exclusão de cozinhas para usar domain services<p>
 * 4.30. Modelando e implementando a inclusão de recursos de restaurantes<p>
 * 5.4. Refatorando o código do projeto para usar o repositório do SDJ<p>
 * 8.5. Simplificando o código com o uso de @ResponseStatus em exceptions<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */	

@RestController
@RequestMapping(value = "/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar(){
		return cozinhaRepository.findAll();
	}
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml(){
		return new CozinhasXmlWrapper(cozinhaRepository.findAll());
	}
	
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable Long cozinhaId) {
		return cadastroCozinha.buscarOuFalhar(cozinhaId);
//		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
//		if (cozinha.isPresent()) {
//			return ResponseEntity.ok(cozinha.get());
//		}
//		return ResponseEntity.notFound().build();
		
//		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//		return ResponseEntity.ok(cozinha);
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
		
//		return ResponseEntity
//				.status(HttpStatus.FOUND)
//				.headers(headers)
//				.build();
		
//		return cozinhaRepository.buscar(cozinhaId);
			
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionar(@RequestBody Cozinha cozinha) {
		cadastroCozinha.salvar(cozinha);
	}
	
	@PutMapping("{id}")
	public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha){
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			
		return cadastroCozinha.salvar(cozinhaAtual);
			
		
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id){
			cadastroCozinha.excluir(id);
	}

}
