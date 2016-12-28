package br.edu.ifpb.sicAgro.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.services.ProdutorService;

/**
 * Faces converter responsável por converter os atributos da entidade @Produtor para
 * string (visualização na pagina) e para objeto. 
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
@FacesConverter(forClass=Produtor.class)
public class ProdutorConverter implements Converter{

	@Inject
	private ProdutorService produtorService;

	/**
	 * 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = Long.parseLong(value);
		try {
			return produtorService.findById(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format(
					"%s é invalido para o produtor", id)), e);
		}
	}

	/**
	 * 
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Produtor) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
