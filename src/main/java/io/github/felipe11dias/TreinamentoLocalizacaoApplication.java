package io.github.felipe11dias;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.felipe11dias.service.CidadeService;

@SpringBootApplication
public class TreinamentoLocalizacaoApplication implements CommandLineRunner{
	
	@Autowired
	private CidadeService cidadeService;
	
	@Override
	public void run(String... args) throws Exception {
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TreinamentoLocalizacaoApplication.class, args);
	}

}
