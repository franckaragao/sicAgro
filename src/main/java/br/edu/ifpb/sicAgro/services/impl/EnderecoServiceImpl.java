package br.edu.ifpb.sicAgro.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.EnderecoDAO;
import br.edu.ifpb.sicAgro.enumerations.States;
import br.edu.ifpb.sicAgro.model.Endereco;
import br.edu.ifpb.sicAgro.services.EnderecoService;

public class EnderecoServiceImpl extends GenericServiceImpl<Endereco, Long> implements EnderecoService {

	private static final long serialVersionUID = 1L;

	public static List<States> states = new ArrayList<>();
	public static List<String> cities =  new ArrayList<>();

	public EnderecoServiceImpl() {
	}

	@Inject
	public EnderecoServiceImpl(EnderecoDAO dao) {
		this.dao = dao;
		states = Arrays.asList(States.values());
	}

	@Override
	public void getCities(Object state, Integer codState) {
		cities.clear();
		if (state != null) {
			for (String citiesFilted : listCities(codState)) {
				cities.add(citiesFilted);
			}
		}
	}

	@Override
	public List<String> listCities(Integer codCity) {
		EnderecoDAO enderecoDAO = (EnderecoDAO) this.dao;
		List<String> result = enderecoDAO.getCities(codCity);
		return result;
	}

	@Override
	public List<Endereco> findByAddress(String anddress) {
		EnderecoDAO enderecoDAO = (EnderecoDAO) this.dao;
		List<Endereco> result = enderecoDAO.findByAddress(anddress);
		return result;
	}
}
