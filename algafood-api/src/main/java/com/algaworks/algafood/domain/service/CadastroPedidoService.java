package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;

/**
 * 12.20. Otimizando a query de pedidos e retornando model resumido na listagem<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */
@Service
public class CadastroPedidoService {
	
	private static final String MSG_PEDIDO_EM_USO = "Pedido de código: %d em uso";
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			pedidoRepository.deleteById(id);
			pedidoRepository.flush();
		} catch(EmptyResultDataAccessException e) {
			throw new PedidoNaoEncontradoException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PEDIDO_EM_USO, id));
		}
	}
	@Transactional
	public void associarPermissao(Long id) {
		try {
			pedidoRepository.deleteById(id);
			pedidoRepository.flush();
		} catch(EmptyResultDataAccessException e) {
			throw new PedidoNaoEncontradoException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PEDIDO_EM_USO, id));
		}
	}
	
	public Pedido buscarOuFalhar(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException(id));
	}
	

}
