package co.uk.olm.group.service;

import co.uk.olm.group.dto.VetDTO;
import co.uk.olm.group.entity.Pet;
import co.uk.olm.group.entity.Vet;
import co.uk.olm.group.repository.PetRepository;
import co.uk.olm.group.repository.VetRepository;
import co.uk.olm.group.util.Mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class VetService {

    private final VetRepository repository;
    private final PetRepository petRepository;

    @Inject
    public VetService(final VetRepository repository, PetRepository petRepository) {
        this.repository = repository;
        this.petRepository = petRepository;
    }

    /**
     * Returns a list of vets.
     * @return a list of vets
     */
    public List<VetDTO> getAll() {
        var vets = repository.listAll();
        return Mapper.getInstance().getObjectMapper().convertValue(vets, List.class);
    }

    /**
     * Returns a vet by id
     * @param id vet pet id
     * @return a vet by id
     */
    public VetDTO getById(final Long id) {
        var vet = repository.findByIdOptional(id).orElseThrow(() -> new EntityNotFoundException("Vet not found!"));
        return Mapper.getInstance().getObjectMapper().convertValue(vet, VetDTO.class);
    }

    /**
     * Save a new entry of Vet.
     * @param vetDTO the vet to be saved
     * @return the vet saved on the database
     */
    @Transactional
    public VetDTO save(final VetDTO vetDTO) {
        final List<Pet> pets = new ArrayList<>();
        if (vetDTO.getPetIds() != null) {
            for (var id : vetDTO.getPetIds()) {
                pets.add(petRepository.findById(id));
            }
        }

        var vet = Mapper.getInstance().getObjectMapper().convertValue(vetDTO, Vet.class);
        vet.setPets(pets);
        repository.persist(vet);
        return Mapper.getInstance().getObjectMapper().convertValue(vet, VetDTO.class);
    }
}