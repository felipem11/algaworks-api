package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

/**
 * 12.8. Desafio: implementando os endpoints de grupos
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	
	@GetMapping
	private List<Grupo> listar(){
		return grupoRepository.findAll();
	}
	
	@GetMapping("{id}")
	private GrupoModel buscar(@PathVariable Long id){
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(id);
		return grupoModelAssembler.toModel(grupo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private GrupoModel adicionar(@RequestBody GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		grupo = cadastroGrupoService.salvar(grupo);
		return grupoModelAssembler.toModel(grupo);
	}
	
	@PutMapping("/{id}")
	private GrupoModel alterar(@PathVariable Long id, 
			@RequestBody GrupoInput grupoInput){
		
		Grupo grupoDB = cadastroGrupoService.buscarOuFalhar(id);
		
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoDB);

		grupoDB = cadastroGrupoService.salvar(grupoDB);
		return grupoModelAssembler.toModel(grupoDB);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void excluir(@PathVariable Long id){
			cadastroGrupoService.excluir(id);
		
	}

}
