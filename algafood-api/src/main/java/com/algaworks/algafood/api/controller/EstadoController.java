package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoIdInput;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ
 * 8.6. Desafio: refatorando os serviços REST<p>
 * @see  "https://github.com/felipem11/algaworks-api"
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
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
	
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;
	
	@GetMapping
	private List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("{id}")
	private EstadoModel buscar(@PathVariable Long id){
		Estado estado = cadastroEstadoService.buscarOuFalhar(id);
		return estadoModelAssembler.toModel(estado);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private EstadoModel adicionar(@RequestBody EstadoIdInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		estado = cadastroEstadoService.salvar(estado);
		return estadoModelAssembler.toModel(estado);
	}
	
	@PutMapping("/{id}")
	private EstadoModel alterar(@PathVariable Long id, 
			@RequestBody EstadoIdInput estadoInput){
		
		Estado estadoDB = cadastroEstadoService.buscarOuFalhar(id);
		
		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoDB);

		estadoDB = cadastroEstadoService.salvar(estadoDB);
		return estadoModelAssembler.toModel(estadoDB);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void excluir(@PathVariable Long id){
			cadastroEstadoService.excluir(id);
		
	}

}
