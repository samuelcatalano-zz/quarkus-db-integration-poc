package co.uk.olm.group.repository;

import co.uk.olm.group.entity.Pet;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class PetRepository implements PanacheRepository<Pet> {

    /**
     * Returns a list of Pets by Person id.
     * @param personId the if of the person
     * @return a list of pets
     */
    public List<Pet> getByPerson(final Long personId) {
        return this.list("person.id", personId);
    }

    /**
     * Returns a list of pets by vet.
     * @param vetId the vet id
     * @return a list of pets by vet
     */
    public List<Pet> getPetsByVet(final Long vetId) {
        final EntityManager em = this.getEntityManager();
        final Query query = em.createNativeQuery("SELECT * FROM pet, vet_pet " +
                                                    "WHERE pet.id = vet_pet.pet_id " +
                                                    "AND vet_pet.vet_id = ?", Pet.class);
        query.setParameter(1, vetId);
        return query.getResultList();
    }
}