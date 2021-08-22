package co.uk.olm.group.resource;

import co.uk.olm.group.dto.PetDTO;
import co.uk.olm.group.exception.BusinessException;
import co.uk.olm.group.exception.ApiException;
import co.uk.olm.group.service.PetService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/pet")
public class PetResource {

    private final PetService service;

    @Inject
    public PetResource(final PetService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        var pets = service.getAll();
        return Response.ok(pets).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        var pet = service.getById(id);
        return Response.ok(pet).build();
    }

    @GET
    @Path("/find-by-person/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByPetsByPerson(@PathParam("id") final Long id) {
        var pet = service.getPetsByPerson(id);
        return Response.ok(pet).build();
    }

    @GET
    @Path("/find-by-vet/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByPetsByVet(@PathParam("id") final Long id) {
        var pet = service.getPetsByVet(id);
        return Response.ok(pet).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(final PetDTO petDTO) throws ApiException {
        try {
            var response = service.save(petDTO);
            return Response.ok(response).build();
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            throw new ApiException(e);
        }
    }
}
