package io.github.felipe11dias.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.felipe11dias.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {
	
	@Query(nativeQuery = true, value = "select c.id_cidade as id, c.nome from tb_cidade as c where c.nome =:nome")
	List<CidadeProjection> findByNomeSqlNativo(@Param("nome") String nome);
	
	List<Cidade> findByNome(String nome);
	
	//@Query("select c from Cidade c where upper(c.nome) like upper(?1)")
	//List<Cidade> findByNomeLike(String nome);
	
	// Customiza a busca para buscar de acordo com localização desejada
	List<Cidade> findByNomeLike(String nome, Sort sort, Pageable pageable);
	
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
