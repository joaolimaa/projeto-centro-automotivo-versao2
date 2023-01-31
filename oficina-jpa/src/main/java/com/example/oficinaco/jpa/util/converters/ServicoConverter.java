package com.example.oficinaco.jpa.util.converters;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.example.oficinaco.jpa.control.ServicoControl;
import com.example.oficinaco.jpa.model.Servico;

@FacesConverter("servicoConverter")
public class ServicoConverter implements Converter {

    private ServicoControl servicoControl = new ServicoControl();
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        Servico servico = servicoControl.getServicoById(value);
        return servico;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return ((Servico) value).getId().toString();
    }

}
