package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;

/**
 * 5.7. Usando as keywords para definir critérios de query methods<p>
 * 5.16. Conhecendo o uso do padrão Specifications (DDD) com SDJ<p>
 * 5.17. Implementando Specifications com SDJ<p>
 * @see  https://github.com/felipem11/algaworks-api
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15 
 */

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
//	/cozinhas/por-nome?nome=Tailandesa
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome){
		return cozinhaRepository.findByNome(nome);
	}

	@GetMapping("/cozinhas/unico-por-nome")
	public Cozinha cozinhasUnicoPorNome(@RequestParam("nome") String nome){
		return cozinhaRepository.findCozinhaByNome(nome).get();
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> cozinhasPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> cozinhasPorNome(String nome, Long cozinhaId){
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
	}
	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> restaurantePrimeiroPorNome(String nome, Long cozinhaId){
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}
	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> restaurantessTop2PorNome(String nome){
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	@GetMapping("/restaurantes/count-por-cozinha")
	public Integer restaurantessCountPorCozinha(Long cozinhaId){
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
	@GetMapping("/restaurantes/existes-por-nome")
	public boolean restaurantessCountPorCozinha(String nome){
		return restauranteRepository.existsByNome(nome);
	}
	
	@GetMapping("/restaurantes/criteria-por-nome")
	public boolean restaurantesCriteriaPorNome(String nome){
		return restauranteRepository.existsByNome(nome);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome){
		var comFreteGratis = new RestauranteComFreteGratisSpec();
		var comNomeSemalhante = new RestauranteComNomeSemelhanteSpec(nome);
		 
		return restauranteRepository.findAll(comFreteGratis.and(comNomeSemalhante));
	}
	
	
}
