package com.example.oficinaco.jpa.util.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.example.oficinaco.jpa.control.OrdemServicoControl;
import com.example.oficinaco.jpa.model.OrdemServico;

@FacesConverter("ordemServicoConverter")
public class OrdemServicoConverter implements Converter {

    OrdemServicoControl ordemServicoControl = new OrdemServicoControl();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        OrdemServico ordemServico = ordemServicoControl.getOrdemServicoById(value);
        return ordemServico;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return ((OrdemServico) value).getId().toString();
    }


}
