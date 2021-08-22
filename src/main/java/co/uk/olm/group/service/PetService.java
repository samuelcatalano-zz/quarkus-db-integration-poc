package co.uk.olm.group.service;

import co.uk.olm.group.dto.PetDTO;
import co.uk.olm.group.entity.Pet;
import co.uk.olm.group.exception.BusinessException;
import co.uk.olm.group.repository.PersonRepository;
import co.uk.olm.group.repository.PetRepository;
import co.uk.olm.group.util.Mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PetService {

    private final PetRepository repository;
    private final PersonRepository personRepository;

    @Inject
    public PetService(final PetRepository repository, final PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
    }

    /**
     * Returns a list of pets.
     * @return a list of pets
     */
    public List<PetDTO> getAll() {
        var pets = repository.listAll();
        return Mapper.getInstance().getObjectMapper().convertValue(pets, List.class);
    }

    /**
     * Returns a pet by id
     * @param id the pet id
     * @return a pet by id
     */
    public PetDTO getById(final Long id) {
        var pet = repository.findByIdOptional(id).orElseThrow(() -> new EntityNotFoundException("Pet not found!"));
        return Mapper.getInstance().getObjectMapper().convertValue(pet, PetDTO.class);
    }

    /**
     * Returns a list of pets by person id.
     * @param personId the person id
     * @return a list of pets
     */
    public List<PetDTO> getPetsByPerson(final Long personId) {
        var pets = repository.getByPerson(personId);
        return Mapper.getInstance().getObjectMapper().convertValue(pets, List.class);
    }

    /**
     * Returns a list of pets by vet id
     * @param vetId the vet id
     * @return a list of pets
     */
    public List<PetDTO> getPetsByVet(final Long vetId) {
        var pets = repository.getPetsByVet(vetId);
        return Mapper.getInstance().getObjectMapper().convertValue(pets, List.class);
    }

    /**
     * Save a new entry of Pet.
     * @param petDTO the pet to be saved
     * @return the pet saved on the database
     */
    @Transactional
    public PetDTO save(final PetDTO petDTO) throws BusinessException {
        if (petDTO.getPersonId() == null) {
            throw new BusinessException("Error persisting new Pet! No person found!");
        }
        var person = personRepository.findById(petDTO.getPersonId());
        var pet = Mapper.getInstance().getObjectMapper().convertValue(petDTO, Pet.class);

        pet.setPerson(person);
        repository.persist(pet);

        return Mapper.getInstance().getObjectMapper().convertValue(pet, PetDTO.class);
    }
}
