package br.com.teste.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.exception.ResourceNotFoundException;
import br.com.teste.model.Person;
import br.com.teste.repository.PersonRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/all")
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
		return ResponseEntity.ok().body(person);
	}

	@PostMapping("/save")
	public Person createPerson(@Valid @RequestBody Person person) {
		return personRepository.save(person);
	}

	/*
	 * @PutMapping("/update/{id}") public ResponseEntity<Person>
	 * updatePerson(@PathVariable(value = "id") Long personId,
	 * 
	 * @Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
	 * Person person = personRepository.findById(personId) .orElseThrow(() -> new
	 * ResourceNotFoundException("Person not found for this id :: " + personId));
	 * person.setEmailId(personDetails.getEmailId());
	 * person.setLastName(personDetails.getLastName());
	 * person.setFirstName(personDetails.getFirstName()); final Person updatedPerson
	 * = personRepository.save(person); return ResponseEntity.ok(updatedPerson); }
	 */

	@PutMapping("/update")
	public Person updatePerson(@Valid @RequestBody Person person) {
		return personRepository.save(person);
	}

	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

		personRepository.delete(person);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
