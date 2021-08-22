package co.uk.olm.group.service;

import co.uk.olm.group.dto.PersonDTO;
import co.uk.olm.group.entity.Person;
import co.uk.olm.group.exception.BusinessException;
import co.uk.olm.group.exception.CPISEntityNotFound;
import co.uk.olm.group.repository.PersonRepository;
import co.uk.olm.group.util.Mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonService {

    private final PersonRepository repository;

    @Inject
    public PersonService(final PersonRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns a list of people.
     * @return a list of people
     */
    public List<PersonDTO> getAll() {
        var people = repository.listAll();
        return Mapper.getInstance().getObjectMapper().convertValue(people, List.class);
    }

    /**
     * Returns a person by id
     * @param id the person id
     * @return a person by id
     */
    public PersonDTO getById(final Long id) {
        var person = repository.findByIdOptional(id)
                                       .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        return Mapper.getInstance().getObjectMapper().convertValue(person, PersonDTO.class);
    }

    /**
     * Find person by name.
     * @param name the name of the person
     * @return a person
     */
    public PersonDTO getByName(final String name) throws BusinessException {
        try {
            var person = repository.findByName(name);
            return Mapper.getInstance().getObjectMapper().convertValue(person, PersonDTO.class);
        } catch (CPISEntityNotFound e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * Save a new entry of Person.
     * @param personDTO the person to be saved
     * @return the person saved on the database
     */
    @Transactional
    public PersonDTO save(final PersonDTO personDTO) {
        var person = Mapper.getInstance().getObjectMapper().convertValue(personDTO, Person.class);
        repository.persist(person);
        return Mapper.getInstance().getObjectMapper().convertValue(person, PersonDTO.class);
    }
}
