package org.curso.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.curso.jakarta.model.Carro;
import org.curso.jakarta.service.CarroService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/carros")
public class CarroController {
    @Inject
    CarroService carroService;

    @GET
    @Produces("application/json")
    public Response getCarros(){
        List<Carro> carros = carroService.listAll();
        if(!carros.isEmpty()) {
            return Response.ok().status(Response.Status.OK).entity(carros).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response createCarro(Carro carro){
        Carro c = carroService.save(carro);
        return Response.ok().status(Response.Status.CREATED).entity(c).build();
    }
    @Path("/{id}")
    @PUT
    @Produces(APPLICATION_JSON)
    public Response updateCarroById(@PathParam("id") Integer id, Carro carro){
        Carro c = carroService.update(id, carro);
        if(c != null ){
            return Response.ok().status(Response.Status.OK).entity(c).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Path("/{id}")
    @GET
    @Produces(APPLICATION_JSON)
    public Response getCarroById(@PathParam("id") Integer id){
        Carro c = carroService.findById(id);
        if(c != null) {
            return Response.ok().status(Response.Status.OK).entity(c).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteCarro(@PathParam("id") Integer id){
        if(carroService.findById(id) != null){
            carroService.delete(id);
            return Response.noContent().status(Response.Status.NO_CONTENT).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
