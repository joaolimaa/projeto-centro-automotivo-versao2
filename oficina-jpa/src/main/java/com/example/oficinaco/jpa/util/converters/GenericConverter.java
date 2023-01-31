package com.example.oficinaco.jpa.util.converters;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;


@Component
@FacesConverter("converter")
public class GenericConverter implements Converter{

    private static Map<String, Object> mapaObj = new HashMap<>();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return mapaObj.get(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            if(value != null){
                Method method = value.getClass().getMethod("getId");
                Object id = method.invoke(value);
                String key = String.format("%s:%s", value.getClass().getName(), id.toString());
                mapaObj.put(key, value);
                return key;
            }else{
                return null;
            }
        } catch (Exception e) {
            if(value != null){
                System.out.printf("%s: %s\n", value.getClass().getName(), value);
            }else{
                System.out.println(value);
            }
            e.printStackTrace();
            return null;
        }

    }

}
