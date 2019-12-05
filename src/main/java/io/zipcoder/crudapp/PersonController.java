package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person p) {
        return new ResponseEntity<>(personRepository.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        return new ResponseEntity<>(personRepository.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getPersonList() {
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person p, @PathVariable int id) {
        if (p.getId() != null) { //updating
            return new ResponseEntity<>(personRepository.save(p), HttpStatus.OK);
        } else {
            return createPerson(p);
        }
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity deletePerson(@PathVariable int id) {
        personRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
