package co.uk.olm.group.resource;

import co.uk.olm.group.dto.VetDTO;
import co.uk.olm.group.service.VetService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/vet")
public class VetResource {

    private final VetService service;

    @Inject
    public VetResource(final VetService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        var vets = service.getAll();
        return Response.ok(vets).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getbyId(@PathParam("id") final Long id) {
        var vet = service.getById(id);
        return Response.ok(vet).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(final VetDTO vetDTO) {
        var response = service.save(vetDTO);
        return Response.ok(response).build();
    }
}
