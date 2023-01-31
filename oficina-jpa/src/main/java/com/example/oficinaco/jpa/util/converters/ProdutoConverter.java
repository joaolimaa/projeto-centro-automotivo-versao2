package com.example.oficinaco.jpa.util.converters;

import javax.faces.convert.FacesConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;



import com.example.oficinaco.jpa.control.ProdutoControl;
import com.example.oficinaco.jpa.model.Produto;

@FacesConverter("produtoConverter")
public class ProdutoConverter implements Converter {

    ProdutoControl produtoControl = new ProdutoControl();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent comp, String value) {
        Produto produto = produtoControl.getProdutoById(value);
        return produto;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent comp, Object value) {
        return ((Produto) value).getId().toString();
    }

}
