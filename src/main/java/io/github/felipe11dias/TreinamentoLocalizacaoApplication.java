package io.github.felipe11dias;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.felipe11dias.entity.Cidade;
import io.github.felipe11dias.repository.CidadeRepository;

@SpringBootApplication
public class TreinamentoLocalizacaoApplication implements CommandLineRunner{
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		salvarCidade();
		listarCidades();
	}
	
	@Transactional
	void salvarCidade() {
		var cidade = new Cidade(1L, "Fortaleza", 2643247L);
		cidadeRepository.save(cidade);
	}
	
	void listarCidades() {
		cidadeRepository.findAll().forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(TreinamentoLocalizacaoApplication.class, args);
	}

}