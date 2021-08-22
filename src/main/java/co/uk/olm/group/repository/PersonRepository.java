package co.uk.olm.group.repository;

import co.uk.olm.group.entity.Person;
import co.uk.olm.group.exception.CPISEntityNotFound;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

@Slf4j
@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    /**
     * Find person by name
     * @param name the name of the person
     * @return a person
     */
    public Person findByName(final String name) throws CPISEntityNotFound {
        try {
            var person = this.find("name", name);
            return person.singleResult();
        } catch (NoResultException e) {
            log.error("Person with name: " +name+ " not found!");
            throw new CPISEntityNotFound("Person with name: " +name+ " not found!");
        }
    }
}
