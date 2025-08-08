package org.curso.jakarta.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import org.curso.jakarta.model.Categoria;

import java.util.List;

@FacesConverter(value = "categoriaConverter", forClass = Categoria.class)
public class CategoriaConverter implements Converter<Categoria> {

    @Override
    public Categoria getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.out.println("Accediendo a converter");
        if(s == null || s.isBlank()){
            return null;
        }
        List<Categoria> categorias = (List<Categoria>) uiComponent.getAttributes().get("categoriasList");
        for(Categoria c: categorias){
            if(c.getId() != null && c.getId().toString().equals(s)){
                return c;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Categoria categoria) {
       if(categoria == null || categoria.getId() == null){
           return "";
       }
       return categoria.getId().toString();
    }
}
