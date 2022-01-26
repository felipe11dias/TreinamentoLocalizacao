package io.github.felipe11dias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.felipe11dias.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
}
