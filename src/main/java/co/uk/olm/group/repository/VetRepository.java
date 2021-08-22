package co.uk.olm.group.repository;

import co.uk.olm.group.entity.Vet;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VetRepository implements PanacheRepository<Vet> {
}