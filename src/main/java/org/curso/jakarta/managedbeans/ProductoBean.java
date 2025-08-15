package org.curso.jakarta.managedbeans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import lombok.Getter;
import lombok.Setter;
import org.curso.jakarta.model.Categoria;
import org.curso.jakarta.model.Producto;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Named
@ViewScoped
public class ProductoBean implements Serializable {
    private List<Producto> productos;
    private Producto productoSeleccionado = new Producto();
    private Producto productoNuevo = new Producto();

    private List<Categoria> categorias;
    private Categoria categoriaSeleccionada;

    private static final String BASE_URL_CATEGORIAS = "http://localhost:8080/productosApp/rest/categorias";
    private static final String BASE_URL = "http://localhost:8080/productosApp/rest/productos";

    private String seccionActiva="productos";

    @PostConstruct
    public void init(){
        listarProductos();
        listarCategorias();
    }

    public void listarCategorias(){
        Client client = ClientBuilder.newClient();
        Response response = client.target(BASE_URL_CATEGORIAS).request(MediaType.APPLICATION_JSON).get();
        if(response.getStatus() == 200){
            categorias = response.readEntity(new GenericType<List<Categoria>>(){});
        }else{
            categorias = new ArrayList<>();
        }
        client.close();
    }

    public void listarProductos(){
        Client client = ClientBuilder.newClient();
        Response response = client.target(BASE_URL).request(MediaType.APPLICATION_JSON).get();
        if(response.getStatus() == 200){
            productos = response.readEntity(new GenericType<List<Producto>>(){});
        }else{
            productos = new ArrayList<>();
        }
        client.close();
    }

    public void crearProducto(){
        System.out.println("Creando producto: "+ categoriaSeleccionada.getNombre());
        productoNuevo.setCategoria(categoriaSeleccionada);
        Client client = ClientBuilder.newClient();

        Response response = client.target(BASE_URL).
                request(MediaType.APPLICATION_JSON).
                post(Entity.entity(productoNuevo,MediaType.APPLICATION_JSON));

        if(response.getStatus() == 201){
            listarProductos();
            productoNuevo = new Producto();
            categoriaSeleccionada = null;
            PrimeFaces.current().ajax().update("formConsulta:tableProductos", "formCrear");
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al insertar",
                    "Codigo: "+response.getStatus());

            FacesContext.getCurrentInstance().addMessage(null,msg);
        }
        client.close();
    }
    public void actualizarProducto(RowEditEvent<Producto> event){
        productoSeleccionado = event.getObject();
        System.out.println("Actualizando producto: "+ productoSeleccionado.getId());
        System.out.println(BASE_URL+ "/"+productoSeleccionado.getId());

        Client client = ClientBuilder.newClient();

        Response response = client.target(BASE_URL+"/"+productoSeleccionado.getId()).
                request(MediaType.APPLICATION_JSON).
                put(Entity.entity(productoSeleccionado, MediaType.APPLICATION_JSON));

        if(response.getStatus() == 200){
            //listarProductos();
            productoSeleccionado = new Producto();
            PrimeFaces.current().ajax().update("formConsulta:tableProductos");
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al actualizar",
                    "Codigo: "+response.getStatus());

            FacesContext.getCurrentInstance().addMessage(null,msg);
        }
        client.close();
    }

    public void eliminarProducto(Long id){
        Client client = ClientBuilder.newClient();

        Response response = client.target(BASE_URL+"/"+id).
                request().
                delete();

        if(response.getStatus() == 204){
            listarProductos();
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al eliminar",
                    "Codigo: "+response.getStatus());

            FacesContext.getCurrentInstance().addMessage(null,msg);
        }
        client.close();

    }
}
