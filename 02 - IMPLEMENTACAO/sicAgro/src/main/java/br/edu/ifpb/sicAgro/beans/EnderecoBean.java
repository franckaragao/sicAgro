package br.edu.ifpb.sicAgro.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.States;
import br.edu.ifpb.sicAgro.model.Endereco;
import br.edu.ifpb.sicAgro.services.EnderecoService;
import br.edu.ifpb.sicAgro.services.impl.EnderecoServiceImpl;

/**
 * Manager bean responsável por gerenciar endereços.
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class EnderecoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<States> states = new ArrayList<>();
	private List<String> cities = new ArrayList<>();
	
	@Inject
	private EnderecoService enderecoService;

	/**
	 * 
	 * @return
	 */
	public List<String> getCities() {
		this.cities = EnderecoServiceImpl.cities;
		return cities;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<States> getStates(){
		this.states = EnderecoServiceImpl.states;
		return states;
	}
	/**
	 * Busca endereços com base na rua digitada.
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeCities(String query){
		List<Endereco> ad =  enderecoService.findByAddress(query);
		HashSet<String> aux = new HashSet<String>();
		
		for (Endereco endereco : ad) {
			aux.add(endereco.getAddress());
		}
		return new ArrayList<String>(aux);
	}
}
