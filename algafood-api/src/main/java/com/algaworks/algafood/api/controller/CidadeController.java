package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ<p>
 * 8.6. Desafio: refatorando os serviços REST<p>
 * 8.10. Afinando a granularidade e definindo a hierarquia das exceptions de negócios<p>
 * @see  "https://github.com/felipem11/algaworks-api"
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
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@GetMapping
	private List<Cidade> listar(){
		return cidadeRepository.findAll(); 
	}
	
	@GetMapping("{id}")
	private CidadeModel buscarCidade(@PathVariable Long id){
		Cidade cidade = cadastroCidade.buscarOuFalhar(id);
		return cidadeModelAssembler.toModel(cidade);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private CidadeModel salvar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			cidade = cadastroCidade.salvar(cidade);
			
			return cidadeModelAssembler.toModel(cidade);
		} catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
	}
	
	@PutMapping("/{id}")
	private CidadeModel alterar(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput){
		Cidade cidadeDB = cadastroCidade.buscarOuFalhar(id);
		
		cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeDB);
		
		try {
			Cidade cidade = cadastroCidade.salvar(cidadeDB);
			return cidadeModelAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void  excluir(@PathVariable Long id){
			cadastroCidade.excluir(id);
		
	}
	
}
