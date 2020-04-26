package com.algaworks.algafood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;
import com.algaworks.algafood.infrastructure.repository.CustomJpaRepositoryimpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryimpl.class)
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}

}
