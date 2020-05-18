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

import com.algaworks.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;
/**
 * 5.5. Desafio: refatorando todos os repositórios para usar SDJ<p>
 * 8.6. Desafio: refatorando os serviços REST<p>
 * 8.10. Afinando a granularidade e definindo a hierarquia das exceptions de negócios<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */


@RestController
@RequestMapping("/formas-pagamentos")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@GetMapping
	private List<FormaPagamento> listar(){
		return formaPagamentoRepository.findAll(); 
	}
	
	@GetMapping("{id}")
	private FormaPagamentoModel buscarFormaPagamento(@PathVariable Long id){
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(id);
		return formaPagamentoModelAssembler.toModel(formaPagamento);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private FormaPagamentoModel salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		try {
			FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
			formaPagamento = cadastroFormaPagamento.salvar(formaPagamento);
			
			return formaPagamentoModelAssembler.toModel(formaPagamento);
		} catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
	}
	
	@PutMapping("/{id}")
	private FormaPagamentoModel alterar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput){
		FormaPagamento formaPagamentoDB = cadastroFormaPagamento.buscarOuFalhar(id);
		
		formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoDB);
		
		try {
			FormaPagamento formaPagamento = cadastroFormaPagamento.salvar(formaPagamentoDB);
			return formaPagamentoModelAssembler.toModel(formaPagamento);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void  excluir(@PathVariable Long id){
			cadastroFormaPagamento.excluir(id);
		
	}
	
}
