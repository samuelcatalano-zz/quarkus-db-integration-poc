package co.uk.olm.group.repository;

import co.uk.olm.group.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepositoryAlternative extends JpaRepository<Pet, Long> {
}