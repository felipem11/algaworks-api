package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

/**
 * 12.9. Desafio: implementando os endpoints de usuarios<p>
 * 12.11. Implementando regra de negócio para evitar usuários com e-mails duplicados<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@Service
public class CadastroUsuarioService {
	
	private static final String MSG_USUARIO_EM_USO = "Usuário de código: %d em uso";
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		
		usuarioRepository.detach(usuario);
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o email: %s", usuario.getEmail()));
		}
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			usuarioRepository.deleteById(id);
			usuarioRepository.flush();
		} catch(EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, id));
		}
	}
	
	@Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        
        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        
        usuario.setSenha(novaSenha);
    }
	
	public Usuario buscarOuFalhar(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

}