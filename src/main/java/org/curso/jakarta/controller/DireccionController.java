package org.curso.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.curso.jakarta.model.Direccion;
import org.curso.jakarta.service.DireccionService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/direcciones")
public class DireccionController {
    @Inject
    DireccionService direccionService;

    @GET
    @Produces("application/json")
    public Response getDirecciones(){
        List<Direccion> direcciones = direccionService.listAll();
        if(!direcciones.isEmpty()) {
            return Response.ok().status(Response.Status.OK).entity(direcciones).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response createDireccion(Direccion direccion){
        Direccion d = direccionService.save(direccion);
        return Response.ok().status(Response.Status.CREATED).entity(d).build();
    }

    @Path("/{id}")
    @PUT
    @Produces(APPLICATION_JSON)
    public Response updateDireccionById(@PathParam("id") Integer id, Direccion direccion){
        Direccion p = direccionService.update(id, direccion);
        if(p != null ){
            return Response.ok().status(Response.Status.OK).entity(p).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Path("/{id}")
    @GET
    @Produces(APPLICATION_JSON)
    public Response getDireccionById(@PathParam("id") Integer id){
        Direccion d = direccionService.findById(id);
        if(d != null) {
            return Response.ok().status(Response.Status.OK).entity(d).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteProducto(@PathParam("id") Integer id){
        if(direccionService.findById(id) != null){
            direccionService.delete(id);
            return Response.noContent().status(Response.Status.NO_CONTENT).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
