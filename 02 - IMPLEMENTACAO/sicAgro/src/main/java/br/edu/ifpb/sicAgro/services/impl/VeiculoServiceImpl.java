package br.edu.ifpb.sicAgro.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.VeiculoDAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.model.Veiculo;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;
import br.edu.ifpb.sicAgro.services.VeiculoService;
import br.edu.ifpb.sicAgro.util.jpa.Transactional;

/**
 * Implementa operações defindas em @VeiculoDAO.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class VeiculoServiceImpl extends GenericServiceImpl<Veiculo, Long> implements VeiculoService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SolicitacaoServicoService solicitacaoServicoService;

	public VeiculoServiceImpl() {
	}

	@Inject
	public VeiculoServiceImpl(VeiculoDAO veiculoDAO) {
		this.dao = veiculoDAO;
	}
	
	/**
	 * Remove um veículo considerando suas restrições.
	 * 
	 */
	@Override
	@Transactional
	public void remove(Veiculo entity) throws SicAgroException {
		List<Object[]> flag = solicitacaoServicoService.getTotalSolicitacoesByMaquina();
		if(flag.size() > 0){
			throw new SicAgroExceptionHandler(
					"Máquina não pode ser removida, a mesma está relacionada com uma alguma solicitação de serviço.");
		}
		dao.remove(entity);
	}

	@Override
	public List<Veiculo> findByIdentification(String identification) {
		VeiculoDAO veiculoDAO = (VeiculoDAO) this.dao;
		List<Veiculo> list = veiculoDAO.findByIdentification(identification);
		return list;
	}

	/**
	 * Atualiza o horimetro de um veiculo.
	 * 
	 */
	@Transactional
	public void setHorimetroVeiculo(Veiculo veiculo, Integer timesWorked) {
		veiculo.setHorimetro(veiculo.getHorimetro() + timesWorked);
		this.update(veiculo);
	}
}
