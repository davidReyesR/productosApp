package org.curso.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.curso.jakarta.model.Categoria;
import org.curso.jakarta.service.CategoriaService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/categorias")
public class CategoriaController {
    @Inject
    CategoriaService categoriaService;

    @GET
    @Produces("application/json")
    public Response getCategorias(){
        List<Categoria> categorias = categoriaService.listAll();
        if(!categorias.isEmpty()) {
            return Response.ok().status(Response.Status.OK).entity(categorias).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response createCategoria(Categoria categoria){
        Categoria c = categoriaService.save(categoria);
        return Response.ok().status(Response.Status.CREATED).entity(c).build();
    }
    @Path("/{id}")
    @PUT
    @Produces(APPLICATION_JSON)
    public Response updateCategoriaById(@PathParam("id") Integer id, Categoria categoria){
        Categoria c = categoriaService.update(id, categoria);
        if(c != null ){
            return Response.ok().status(Response.Status.OK).entity(c).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Path("/{id}")
    @GET
    @Produces(APPLICATION_JSON)
    public Response getCategoriaById(@PathParam("id") Integer id){
        Categoria c = categoriaService.findById(id);
        if(c != null) {
            return Response.ok().status(Response.Status.OK).entity(c).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteCategoria(@PathParam("id") Integer id){
        if(categoriaService.findById(id) != null){
            categoriaService.delete(id);
            return Response.noContent().status(Response.Status.NO_CONTENT).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
