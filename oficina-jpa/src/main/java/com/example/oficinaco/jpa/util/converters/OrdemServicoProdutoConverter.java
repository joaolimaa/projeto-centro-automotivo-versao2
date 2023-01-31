package com.example.oficinaco.jpa.util.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.example.oficinaco.jpa.control.OrdemServicoProdutoControl;
import com.example.oficinaco.jpa.model.OrdemServico;
import com.example.oficinaco.jpa.model.OrdemServicoProduto;

@FacesConverter("ordemServicoProdutoConverter")
public class OrdemServicoProdutoConverter implements Converter {

    OrdemServicoProdutoControl ordemServicoProdutoControl = new OrdemServicoProdutoControl();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        OrdemServicoProduto ordemServicoProduto = ordemServicoProdutoControl.getOrdemServicoById(value);
        return ordemServicoProduto;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return ((OrdemServicoProduto) value).getId().toString();
    }

}
