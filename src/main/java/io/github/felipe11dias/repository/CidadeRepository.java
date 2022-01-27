package io.github.felipe11dias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.felipe11dias.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	List<Cidade> findByNome(String nome);
	
	//@Query("select c from Cidade c where upper(c.nome) like upper(?1)")
	//List<Cidade> findByNomeLike(String nome);
	
	// Customiza a busca para buscar de acordo com localização desejada
	List<Cidade> findByNomeLike(String nome);
	
	// Começa com esse nome
	List<Cidade> findByNomeStartingWith(String nome);
	
	// Encerra com esse nome
	List<Cidade> findByNomeEndingWith(String nome);
	
	// Contem com esse nome
	List<Cidade> findByNomeContainingWith(String nome);
	
	List<Cidade> findByHabitantes(Long habitantes);
	
	// Menor ou igual
	List<Cidade> findByHabitantesLessThanEqualAndNomeLike(Long habitantes, String nome);
	
	// Menor ou igual
	List<Cidade> findByHabitantesLessThanEqual(Long habitantes);
	
	// Maior ou igual
	List<Cidade> findByHabitantesGreaterThanEqual(Long habitantes);
	
	// Menor que
	List<Cidade> findByHabitantesLessThan(Long habitantes);
	
	// Maior que
	List<Cidade> findByHabitantesGreaterThan(Long habitantes);
}
