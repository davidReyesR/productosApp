package org.curso.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.curso.jakarta.model.Cliente;
import org.curso.jakarta.service.ClienteService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/clientes")
public class ClienteController {
    @Inject
    ClienteService clienteService;

    @GET
    @Produces("application/json")
    public Response getCliente(){
        List<Cliente> clientes = clienteService.listAll();
        if(!clientes.isEmpty()) {
            return Response.ok().status(Response.Status.OK).entity(clientes).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response createCliente(Cliente cliente){
        Cliente c = clienteService.save(cliente);
        return Response.ok().status(Response.Status.CREATED).entity(c).build();
    }
    @Path("/{id}")
    @PUT
    @Produces(APPLICATION_JSON)
    public Response updateClienteById(@PathParam("id") Integer id, Cliente cliente){
        Cliente c = clienteService.update(id, cliente);
        if(c != null ){
            return Response.ok().status(Response.Status.OK).entity(c).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Path("/{id}")
    @GET
    @Produces(APPLICATION_JSON)
    public Response getClienteById(@PathParam("id") Integer id){
        Cliente c = clienteService.findById(id);
        if(c != null) {
            return Response.ok().status(Response.Status.OK).entity(c).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteCategoria(@PathParam("id") Integer id){
        if(clienteService.findById(id) != null){
            clienteService.delete(id);
            return Response.noContent().status(Response.Status.NO_CONTENT).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
