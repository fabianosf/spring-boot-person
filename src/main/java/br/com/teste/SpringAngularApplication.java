package br.com.teste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.teste.model.Person;
import br.com.teste.repository.PersonRepository;

@SpringBootApplication
public class SpringAngularApplication implements CommandLineRunner{

	
	@SuppressWarnings("unused")
	@Autowired
	private PersonRepository personRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringAngularApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Person p1 = new Person("Maria","Flores","maria@email.com");
		Person p2 = new Person("Pedro", "Rocha","pedro@email.com");
		Person p3 = new Person("Joana","Linda","joana@emai.com");
		
		//personRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}
	
	
	
	

}
