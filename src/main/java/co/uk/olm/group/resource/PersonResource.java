package co.uk.olm.group.resource;

import co.uk.olm.group.dto.PersonDTO;
import co.uk.olm.group.exception.ApiException;
import co.uk.olm.group.exception.BusinessException;
import co.uk.olm.group.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
public class PersonResource {

    private final PersonService service;

    @Inject
    public PersonResource(final PersonService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        var people = service.getAll();
        return Response.ok(people).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        var person = service.getById(id);
        return Response.ok(person).build();
    }

    @GET
    @Path("/find-by-name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") final String name) throws ApiException {
        try {
            var person = service.getByName(name);
            return Response.ok(person).build();
        } catch (BusinessException e) {
            throw new ApiException(e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(final PersonDTO personDTO) {
        var response = service.save(personDTO);
        return Response.ok(response).build();
    }
}
