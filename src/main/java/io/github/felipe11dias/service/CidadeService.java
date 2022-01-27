package io.github.felipe11dias.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.github.felipe11dias.entity.Cidade;
import io.github.felipe11dias.repository.CidadeRepository;

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
}
