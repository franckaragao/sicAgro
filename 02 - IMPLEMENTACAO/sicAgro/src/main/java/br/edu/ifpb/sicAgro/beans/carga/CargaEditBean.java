package br.edu.ifpb.sicAgro.beans.carga;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.Agencys;
import br.edu.ifpb.sicAgro.enumerations.MeasurementType;
import br.edu.ifpb.sicAgro.model.Carga;
import br.edu.ifpb.sicAgro.model.ItemCarga;
import br.edu.ifpb.sicAgro.model.OrigemCarga;
import br.edu.ifpb.sicAgro.model.Produto;
import br.edu.ifpb.sicAgro.services.CargaService;
import br.edu.ifpb.sicAgro.services.EnderecoService;
import br.edu.ifpb.sicAgro.services.ProdutoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class CargaEditBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CargaService cargaService;

	@Inject
	private ProdutoService produtoService;
	
	@Inject
	private EnderecoService enderecoService;

	private Carga carga;
	private List<Produto> produtos;
	private ItemCarga itemCarga;
	private ItemCarga selectedItemCarga = new ItemCarga();

	private List<MeasurementType> measurements = new ArrayList<>();
	private List<Agencys> agencys = new ArrayList<>();

	public CargaEditBean() {
		measurements = Arrays.asList(MeasurementType.values());
		agencys = Arrays.asList(Agencys.values());
		produtos = new ArrayList<Produto>();
	}

	@PostConstruct
	public void init() {
		this.listOfProdutos();
	}

	/**
	 * 
	 */
	public void preRenderView() {
		if (carga == null) {
			carga = new Carga();
			carga.setOriginLoad(new OrigemCarga());
			carga.setItensCarga(new ArrayList<ItemCarga>());
		}else{
			this.loadCities();
		}
	}

	/**
	 * 
	 */
	public void save() {
		if (carga.getId() != null) {
			cargaService.update(carga);
			MessageUtils.messageSucess("Carga atualizada com sucesso.");

		} else {
			cargaService.add(carga);
			MessageUtils.messageSucess("Carga adicionada com sucesso.");
		}
		
		JSFUtils.rederTo("cargas.xhtml");
	}

	public void resetItemCarga() {
		this.itemCarga = selectedItemCarga;
		this.itemCarga.setQuantidadeDisp(selectedItemCarga.getQuantity());
		this.selectedItemCarga = new ItemCarga();
	}
	
	public void listOfProdutos() {
		this.produtos = produtoService.findAll();
	}
	
	public void loadCities(){
		if(carga.getOriginLoad().getState() != null)
			enderecoService.getCities(carga.getOriginLoad().getState(), carga.getOriginLoad().getState().getCodigo());
	}

	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}

	public List<MeasurementType> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<MeasurementType> measurements) {
		this.measurements = measurements;
	}

	public List<Agencys> getAgencys() {
		return agencys;
	}

	public void setAgencys(List<Agencys> agencys) {
		this.agencys = agencys;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public ItemCarga getItemCarga() {
		return itemCarga;
	}

	public void setItemCarga(ItemCarga itemCarga) {
		this.itemCarga = itemCarga;
	}

	public ItemCarga getSelectedItemCarga() {
		return selectedItemCarga;
	}

	public void setSelectedItemCarga(ItemCarga selectedItemCarga) {
		this.selectedItemCarga = selectedItemCarga;
	}
}
