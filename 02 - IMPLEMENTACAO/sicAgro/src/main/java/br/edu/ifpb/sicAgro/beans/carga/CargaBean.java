package br.edu.ifpb.sicAgro.beans.carga;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.Carga;
import br.edu.ifpb.sicAgro.services.CargaService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * Manager Bean responsável por gerenciar listagem e busca de cargas. 
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
public class CargaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CargaService cargaService;

	private List<Carga> cargas;

	private Carga selectedCarga;

	
	@PostConstruct
	public void init(){
		this.listOfCargas();
	}
	
	/**
	 * 
	 * @throws SicAgroException
	 */
	public void remove() throws SicAgroException{
		cargaService.remove(selectedCarga);
		MessageUtils.messageSucess("Carga removida com sucesso.");
		JSFUtils.rederTo("cargas.xhtml");
	}
	
	/**
	 * <pre>
	 * Utilizado como solução pra conseguir passar um paramtro apartir do 
	 * manager bean, devido a forma que a linha da datatable é selecionada,
	 * desta forma sem usar um componente que tenha um outcome, tem-se a necessidade
	 * de fazer o manager bean redireiconar para outra página, diante isso o parametro
	 * deve ser passado do manager bean.
	 * </pre>
	 */
	public void renderTo() {
		JSFUtils.rederTo("cargaView.xhtml");
		JSFUtils.setParam("carga", selectedCarga);
	}
	
	public void listOfCargas(){
		this.cargas = cargaService.findAll();
	}

	/**
	 * @return the cargas
	 */
	public List<Carga> getCargas() {
		return cargas;
	}

	/**
	 * @param cargas
	 *            the cargas to set
	 */
	public void setCargas(List<Carga> cargas) {
		this.cargas = cargas;
	}

	/**
	 * @return the selectedCarga
	 */
	public Carga getSelectedCarga() {
		return selectedCarga;
	}

	/**
	 * @param selectedCarga
	 *            the selectedCarga to set
	 */
	public void setSelectedCarga(Carga selectedCarga) {
		this.selectedCarga = selectedCarga;
	}

}
