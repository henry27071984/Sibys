package sibys.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import sibys.model.entity.UnidadMedida;
import sibys.model.repository.UnidadMedidaRepository;
import sibys.service.UnidadMedidaService;

@Named
public class UnidadMedidaServiceImpl implements UnidadMedidaService, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadMedidaRepository unidadMedidaRepository;

	@Transactional
	@Override
	public Integer insert(UnidadMedida t) throws Exception {
		return unidadMedidaRepository.insert(t);
	}

	@Transactional
	@Override
	public Integer update(UnidadMedida t) throws Exception {
		return unidadMedidaRepository.update(t);
	}

	@Transactional
	@Override
	public Integer delete(UnidadMedida t) throws Exception {
		return unidadMedidaRepository.delete(t);
	}

	@Override
	public List<UnidadMedida> findAll() throws Exception {
		return unidadMedidaRepository.findAll();
	}

	@Override
	public UnidadMedida findById(UnidadMedida t) throws Exception {
		return unidadMedidaRepository.findByID(t);
	}

	@Override
	public UnidadMedida findByNombre(String nombre) throws Exception {
		return unidadMedidaRepository.findByNombre(nombre);
	}
	
	@Override
	public UnidadMedida findByIds(Integer id) throws Exception {
		return unidadMedidaRepository.findByIds(id);
	}

}
