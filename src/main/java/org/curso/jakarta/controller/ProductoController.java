package org.curso.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.curso.jakarta.model.Producto;
import org.curso.jakarta.service.ProductoService;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/productos")
public class ProductoController {
    @Inject
    ProductoService productoService;

    @GET
    @Produces("application/json")
    public Response getProductos(){
        List<Producto> productos = productoService.listAll();
        if(!productos.isEmpty()) {
            return Response.ok().status(Response.Status.OK).entity(productos).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response createProducto(Producto producto){
        Producto p = productoService.save(producto);
        return Response.ok().status(Response.Status.CREATED).entity(p).build();
    }

    @Path("/{id}")
    @PUT
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response updateProducto(@PathParam("id") Integer id, Producto producto){
        if(!id.equals(producto.getId())){
            return Response.status(Response.Status.BAD_REQUEST).
                    entity("El ID del URL no coincide con el ID del cuerpo del producto").
                    build();
        }
        Producto p = productoService.update(producto);
        if(p != null ){
            return Response.ok().status(Response.Status.OK).entity(p).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Path("/{id}")
    @GET
    @Produces(APPLICATION_JSON)
    public Response getProductoById(@PathParam("id") Integer id){
        Producto p = productoService.findById(id);
        if(p != null) {
            return Response.ok().status(Response.Status.OK).entity(p).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deleteProducto(@PathParam("id") Integer id){
        if(productoService.findById(id) != null){
            productoService.delete(id);
            return Response.noContent().status(Response.Status.NO_CONTENT).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
