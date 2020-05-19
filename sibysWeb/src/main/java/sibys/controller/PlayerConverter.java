package sibys.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import sibys.model.entity.Producto;

@FacesConverter("convertP")
public class PlayerConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				Producto product = new Producto();
				product.setId(Integer.parseInt(value));
				return product;
			} catch (Exception e) {
				throw new ConverterException("Error", e);
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Producto) {
			return ((Producto) value).getId().toString();
		}
		return null;

	}


}