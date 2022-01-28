package io.github.felipe11dias.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.github.felipe11dias.entity.Cidade;
import io.github.felipe11dias.repository.CidadeRepository;
import io.github.felipe11dias.repository.specs.CidadeSpecs;

@Service
public class CidadeService {
	
	private CidadeRepository cidadeRepository;

	public CidadeService(CidadeRepository cidadeRepository) {
		this.cidadeRepository = cidadeRepository;
	}
	
	@Transactional
	void salvarCidade() {
		var cidade = new Cidade(1L, "Fortaleza", 2643247L);
		cidadeRepository.save(cidade);
	}
	
	void listarCidadesPorNomeComeco() {
		cidadeRepository.findByNomeStartingWith("For").forEach(System.out::println);
	}
	
	void listarCidadesPorNomeFim() {
		cidadeRepository.findByNomeEndingWith("za").forEach(System.out::println);
	}
	
	void listarCidadesPorNomeContem() {
		cidadeRepository.findByNomeEndingWith("a").forEach(System.out::println);
	}
	
	void listarCidadesPorNomeCustomiza() {
		Pageable pageable = PageRequest.of(0, 10);
		cidadeRepository.findByNomeLike("%a%", Sort.by("habitantes"), pageable).forEach(System.out::println);
	}
	
	void listarCidadesPorNome() {
		cidadeRepository.findByNome("Fortaleza").forEach(System.out::println);
	}
	
	void listarCidadesPorQuantidadeMenorHabitantes() {
		cidadeRepository.findByHabitantesLessThan(10000000L).forEach(System.out::println);
	}
	
	void listarCidadesPorQuantidadeMaiorHabitantes() {
		cidadeRepository.findByHabitantesGreaterThan(10000000L).forEach(System.out::println);
	}
	
	void listarCidadesPorHabitantes() {
		cidadeRepository.findByHabitantes(80000000L).forEach(System.out::println);
	}
	
	void listarCidades() {
		cidadeRepository.findAll().forEach(System.out::println);
	}
	
	public List<Cidade> filtroDinamico(Cidade cidade) {
		ExampleMatcher matcher = ExampleMatcher
											.matching()
											.withIgnoreCase()
											.withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
		Example<Cidade> exampleCidade = Example.of(cidade, matcher);
		return cidadeRepository.findAll(exampleCidade);
	}
	
	void listarCidadeByNomeAndHabitantesSpec() {
		Specification<Cidade> specs = CidadeSpecs.nomeEqual("Fortaleza").and(CidadeSpecs.habitantesGreaterThan(1000L));
		cidadeRepository.findAll(specs).forEach(System.out::println);
	}
	
	void listarCidadeSpecsFiltroDinamico(Cidade filtro) {
		Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(filtro.getId() != null) {
			specs = specs.and(CidadeSpecs.idEqual(filtro.getId()));
		}
		
		if(StringUtils.hasText(filtro.getNome())) {
			specs = specs.and(CidadeSpecs.nomeLike(filtro.getNome()));
		}
		
		if(filtro.getHabitantes() != null) {
			specs = specs.and(CidadeSpecs.habitantesGreaterThan(filtro.getHabitantes()));
		}
		
		cidadeRepository.findAll(specs).forEach(System.out::println);
	}
}
