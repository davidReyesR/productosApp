package org.curso.jakarta.managedbeans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
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

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
public class CategoriaBean implements Serializable {
    private List<Categoria> categorias;
    private Categoria categoriaSeleccionada;
    private Categoria categoriaNueva = new Categoria();

    private static final String BASE_URL_CATEGORIAS = "http://localhost:8080/productosApp/rest/categorias";
    @Inject ProductoBean productoBean;

    @PostConstruct
    public void init(){
        listarCategorias();
    }
    public void listarCategorias(){
        Client client = ClientBuilder.newClient();
        Response response = client.target(BASE_URL_CATEGORIAS)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if(response.getStatus() == 200){
            categorias = response.readEntity(new GenericType<List<Categoria>>(){});
        }else{
            categorias = new ArrayList<>();
        }
        client.close();
    }
    public void crearCategoria(){
        Client client = ClientBuilder.newClient();

        Response response = client.target(BASE_URL_CATEGORIAS).
                request(MediaType.APPLICATION_JSON).
                post(Entity.entity(categoriaNueva,MediaType.APPLICATION_JSON));

        if(response.getStatus() == 201){
            listarCategorias();
            productoBean.listarCategorias();
            categoriaNueva = new Categoria();;
            PrimeFaces.current().ajax().update("formCategoria");
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al crear categoria",
                    "Codigo: "+response.getStatus());

            FacesContext.getCurrentInstance().addMessage(null,msg);
        }
        client.close();
    }
    public void actualizarCategoria(RowEditEvent<Categoria> event){
        categoriaSeleccionada = event.getObject();
        System.out.println("Actualizando categoria: "+ categoriaSeleccionada.getId());
        System.out.println(BASE_URL_CATEGORIAS+ "/"+categoriaSeleccionada.getId());

        Client client = ClientBuilder.newClient();

        Response response = client.target(BASE_URL_CATEGORIAS+"/"+categoriaSeleccionada.getId()).
                request(MediaType.APPLICATION_JSON).
                put(Entity.entity(categoriaSeleccionada, MediaType.APPLICATION_JSON));

        if(response.getStatus() == 200){
            //listarCategorias();
            categoriaSeleccionada = new Categoria();
            PrimeFaces.current().ajax().update("formListaCategoria");
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al actualizar categoria",
                    "Codigo: "+response.getStatus());

            FacesContext.getCurrentInstance().addMessage(null,msg);
        }
        client.close();
    }

    public void eliminarCategoria(Long id){
        Client client = ClientBuilder.newClient();

        Response response = client.target(BASE_URL_CATEGORIAS+"/"+id).
                request().
                delete();

        if(response.getStatus() == 204){
            listarCategorias();
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error al eliminar categoria",
                    "Codigo: "+response.getStatus());

            FacesContext.getCurrentInstance().addMessage(null,msg);
        }
        client.close();

    }

}
