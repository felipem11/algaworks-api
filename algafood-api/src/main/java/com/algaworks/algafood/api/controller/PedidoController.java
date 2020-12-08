package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 12.20. Otimizando a query de pedidos e retornando model resumido na listagem<p>
 * 13.2. Limitando os campos retornados pela API com @JsonFilter do Jackson<p>
 * 13.3. Limitando os campos retornados pela API com Squiggly<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;


//	@GetMapping
//	private MappingJacksonValue listar(@RequestParam(required = false) String fields){
//		List<Pedido> todosPedidos = pedidoRepository.findAll();
//		List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
//
//		MappingJacksonValue pedidoWrapper = new MappingJacksonValue(pedidosModel);
//
//		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//
//		if (StringUtils.isNotBlank(fields)){
//			filterProvider.addFilter("pedidoFIlter",
//					SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(",")));
//		}
//
//		pedidoWrapper.setFilters(filterProvider);
//
//		return pedidoWrapper;
//	}

	@GetMapping
	private List<PedidoResumoModel> listar(){
		List<Pedido> todosPedidos = pedidoRepository.findAll();

		return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

		return pedidoModelAssembler.toModel(pedido);
	}
	

}
